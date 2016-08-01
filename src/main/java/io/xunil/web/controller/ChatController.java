package io.xunil.web.controller;

import io.xunil.web.memory.ChatSessions;
import io.xunil.web.memory.ConnectionNegotiation;
import io.xunil.web.memory.model.ChatSession;
import io.xunil.web.presentation.model.*;
import io.xunil.web.util.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.UUID;

/**
 * Created on 7/3/16.
 */
public class ChatController {
    private static final Logger log = LogManager.getLogger(ChatController.class);
    
    private ChatSessions chatSessions;
    
    public ChatController(ChatSessions chatSessions) {
        this.chatSessions = chatSessions;
    }
    
    public ChatRegistrationResponse register(ChatRegistrationRequest chatRegistration) {
        log.info("Registering new chat client");
        ChatSession session = new ChatSession();
        Random r = new Random();
        
        byte[] serverSeed = new byte[20];
        r.nextBytes(serverSeed);
        byte[] clientSeed = chatRegistration.getRandomSeed().getBytes();
        byte[] newSeed = new byte[clientSeed.length + serverSeed.length];
        
        System.arraycopy(clientSeed, 0, newSeed, 0, clientSeed.length);
        System.arraycopy(serverSeed, 0, newSeed, clientSeed.length, serverSeed.length);
        
        session.setPublicKey(chatRegistration.getPublicKey());
        session.setUuid(UUID.nameUUIDFromBytes(newSeed).toString());
        
        chatSessions.addSession(session);
        
        ChatRegistrationResponse response = new ChatRegistrationResponse();
        response.setUuid(session.getUuid());
        return response;
    }
    
    private ChatConnectionResponse reject(boolean timeout) {
        // Delaying so that it's harder to tell the difference between a user rejecting
        // a request and a user who doesn't exist or a user already in a conversation
        Random r = new Random();
        Integer sleepAmount = 10 * (timeout ? 2 : 1);
        try {
            Thread.sleep(r.nextInt(sleepAmount) * 1000 + r.nextInt(1000));
        }
        catch (InterruptedException e) {
            log.warn("Sleep for ChatController.reject() threw exception: ", e);
        }
        ChatConnectionResponse response = new ChatConnectionResponse();
        response.setAnswer("rejected");
        return response;
    }
    
    public ChatConnectionResponse connect(ChatConnectionRequest chatConnectionRequest) {
        String requester = chatConnectionRequest.getFrom();
        String recipient = chatConnectionRequest.getConnectWith();
        ChatSession recipientSession = chatSessions.getSession(recipient);
        ChatSession requesterSession = chatSessions.getSession(requester);
        if (null == recipientSession) {
            return reject(false);
        }
        
        
        
        if (!ConnectionNegotiation.lockUser(requester)) {
            return reject(false);
        }
        
        if (!ConnectionNegotiation.lockUser(recipient)) {
            ConnectionNegotiation.unlockUser(requester);
            return reject(false);
        }
        
        ConnectionNegotiation.newConnection(requester, recipient);
        
        ChatMessage message = new ChatMessage();
        message.setType("connectionRequest");
        message.setFrom(chatConnectionRequest.getFrom());
        message.setContent(JSON.getString(requesterSession.getPublicKey())); // A bit of a hack
        
        recipientSession.getSession().getAsyncRemote().sendText(JSON.getString(message));
        int counter = 0;
        while (ConnectionNegotiation.waitingForAnswer(requester, recipient) && counter++ < 20) {
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                log.warn("Sleep for ChatController.connect(ChatConnectionRequest) threw exception: ", e);
            }
        }
        
        Boolean answer = ConnectionNegotiation.answerConfirmed(requester, recipient);
        if (null == answer) {
            ConnectionNegotiation.unlockUser(requester);
            ConnectionNegotiation.unlockUser(recipient);
            return reject(true);
        }
        
        ChatConnectionResponse response = new ChatConnectionResponse();
        if (answer) {
            response.setPublicKey(recipientSession.getPublicKey());
            response.setAnswer("accepted");
        }
        else {
            ConnectionNegotiation.unlockUser(requester);
            ConnectionNegotiation.unlockUser(recipient);
            response.setAnswer("rejected");
        }
        return response;
    }
}

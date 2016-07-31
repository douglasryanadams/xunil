package io.xunil.web.controller;

import io.xunil.web.memory.ChatSessions;
import io.xunil.web.presentation.model.ChatRegistrationRequest;
import io.xunil.web.presentation.model.ChatRegistrationResponse;
import io.xunil.web.memory.model.ChatSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.UUID;

/**
 * Created on 7/3/16.
 */
public class ChatController {
    private static final Logger log = LogManager.getLogger(ChatController.class);

    private ChatSessions sessions;

    public ChatController(ChatSessions sessions) {
        this.sessions = sessions;
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

        sessions.addSession(session);

        ChatRegistrationResponse response = new ChatRegistrationResponse();
        response.setUuid(session.getUuid());
        return response;
    }
}

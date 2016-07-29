package io.xunil.web.presentation.websocket;

import io.xunil.web.controller.ChatMessageController;
import io.xunil.web.memory.Sessions;
import io.xunil.web.presentation.model.ChatMessage;
import io.xunil.web.util.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created on 5/28/16.
 */

@ServerEndpoint("/chat")
public class ChatSocket {

    private static final Logger log = LogManager.getLogger(ChatSocket.class);
    private Sessions sessions;
    private ChatMessageController controller;

    public ChatSocket() {
        super();
        log.info("Constructing Chat Socket");
        sessions = Sessions.getInstance();
        controller = new ChatMessageController(sessions);
    }

    @OnOpen
    public void open(Session session) {
        log.info("Opening Chat Socket.");
        controller.openChatHandshake(session);
    }

    @OnClose
    public void close(Session session) {
        log.info("Closing Chat Socket.");
        sessions.closeSession(session.getId());
    }

    @OnError
    public void onError(Throwable error) {
        log.warn("Error with Chat Socket: {}", error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        log.debug("    Received Chat Socket Message");
        ChatMessage chatMessage = JSON.getObject(message, ChatMessage.class);
        controller.processMessage(chatMessage, session);
    }
}

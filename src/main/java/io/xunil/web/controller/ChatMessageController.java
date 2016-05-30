package io.xunil.web.controller;

import io.xunil.web.memory.Sessions;
import io.xunil.web.presentation.model.ChatMessage;
import io.xunil.web.util.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.Session;
import java.util.UUID;

/**
 * Created on 5/28/16.
 */
public class ChatMessageController {
    private static final Logger log = LogManager.getLogger(ChatMessageController.class);

    private Sessions sessions;

    public ChatMessageController(Sessions sessions) {
        this.sessions = sessions;
    }

    public void openChatHandshake(Session session) {
        ChatMessage message = new ChatMessage();
        UUID id = UUID.randomUUID();
        String idString = id.toString();
        message.setContent("Chat Initiated");
        message.setTo(idString);
        sessions.addSession(idString, session);
        session.getAsyncRemote().sendText(JSON.getString(message));
        log.debug("    replied to chat handshake");
    }

    public void processMessage(ChatMessage message) {
        String target_id = message.getTo();
        Session session = sessions.getSession(target_id);
        message.setTo(null);
        session.getAsyncRemote().sendText(JSON.getString(message));
        log.debug("    sent chat message to target");
    }
}

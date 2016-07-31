package io.xunil.web.controller;

import io.xunil.web.memory.ChatSessions;
import io.xunil.web.memory.model.ChatSession;
import io.xunil.web.presentation.model.ChatMessage;
import io.xunil.web.util.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.Session;

/**
 * Created on 5/28/16.
 */
public class ChatMessageController {
    private static final Logger log = LogManager.getLogger(ChatMessageController.class);

    private ChatSessions sessions;

    public ChatMessageController(ChatSessions sessions) {
        this.sessions = sessions;
    }

    public void openChatHandshake(Session session) {
        ChatMessage message = new ChatMessage();
        message.setType("registration");
        session.getAsyncRemote().sendText(JSON.getString(message));
        log.debug("    replied to chat handshake");
    }

    public void processMessage(ChatMessage message, Session session) {
        switch (message.getType()) {
            case "chat":
                String targetId = message.getTo();
                Session recipient = sessions.getSession(targetId).getSession();
                // TODO If recipient does not exist, or has not been authorized (later) return error
                message.setTo(null);
                recipient.getAsyncRemote().sendText(JSON.getString(message));
                log.debug("    sent chat message to target");
                break;
            case "registration":
                String uuid = message.getContent();
                String websocketId = session.getId();
                ChatSession thisChatSession = sessions.getSession(uuid);
                if (thisChatSession == null) {
                    // TODO: Deal with this in some sane way later
                    log.error("Invalid Chat Session attempted to register");
                    break;
                }
                thisChatSession.setSession(session);
                sessions.addWebsocketSessionId(websocketId, uuid);
                log.debug("    chat client registered");
                break;
            default:
                log.warn("Invalid message type provided: {}", message.getType());
        }

    }
}

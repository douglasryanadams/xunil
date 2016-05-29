package io.xunil.web.controller;

import io.xunil.web.memory.Sessions;
import io.xunil.web.presentation.model.ChatMessage;

import javax.websocket.Session;

/**
 * Created on 5/28/16.
 */
public class ChatMessageController {

    private Sessions sessions;

    public ChatMessageController(Sessions sessions) {
        this.sessions = sessions;
    }

    public void processMessage(ChatMessage message) {
        String target_id = message.getTo();
        Session session = sessions.getSession(target_id);
        session.getAsyncRemote().sendText(message.getContent());
    }
}

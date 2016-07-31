package io.xunil.web.controller;

import io.xunil.web.memory.RosterSessions;
import io.xunil.web.memory.ChatSessions;
import io.xunil.web.presentation.model.RosterMessage;
import io.xunil.web.util.JSON;

import javax.websocket.Session;

/**
 * Created on 7/13/16.
 */
public class RosterController {

    private ChatSessions sessions;
    private RosterSessions rosterSessions;

    public RosterController(ChatSessions sessions, RosterSessions rosterSessions) {
        this.sessions = sessions;
        this.rosterSessions = rosterSessions;
    }

    public void hideMe(String id) {
        sessions.hideSession(id);
        broadcastVisibleList();
    }

    public void processMessage(RosterMessage message, Session s) {
        switch (message.getType()) {
            case "showMe":
                rosterSessions.setUUID(s, message.getSenderId());
                sessions.showSession(message.getSenderId());
                broadcastVisibleList();
                break;
            case "hideMe":
                hideMe(message.getSenderId());
                break;
        }
    }
    
    public void connectionTransaction(Session session) {
        RosterMessage message = getRosterMessage();
        String messageText = JSON.getString(message);
        session.getAsyncRemote().sendText(messageText);
    }
    
    private RosterMessage getRosterMessage() {
        RosterMessage message = new RosterMessage();
        message.setSenderId("server");
        message.setType("rosterUpdate");
        message.setVisibleRoster(sessions.getVisibleSessions());
        return message;
    }

    private void broadcastVisibleList() {
        RosterMessage message = getRosterMessage();
        String messageText = JSON.getString(message);
        for (Session s : rosterSessions.getRosterSockets()) {
            s.getAsyncRemote().sendText(messageText);
        }
    }

}

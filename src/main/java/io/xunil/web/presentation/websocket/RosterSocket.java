package io.xunil.web.presentation.websocket;

import io.xunil.web.controller.RosterController;
import io.xunil.web.memory.RosterSessions;
import io.xunil.web.memory.ChatSessions;
import io.xunil.web.presentation.model.RosterMessage;
import io.xunil.web.util.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created on 7/13/16.
 */
@ServerEndpoint("/roster")
public class RosterSocket {

    private static final Logger log = LogManager.getLogger(RosterSocket.class);
    private RosterSessions rosterSessions;
    private ChatSessions chatSessions;
    private RosterController controller;

    public RosterSocket() {
        super();
        log.info("Constructing Roster Socket");
        rosterSessions = RosterSessions.getInstance();
        chatSessions = ChatSessions.getInstance();
        controller = new RosterController(chatSessions, rosterSessions);
    }

    @OnOpen
    public void open(Session session) {
        log.info("Opening Roster Socket");
        rosterSessions.add(session);
        controller.connectionTransaction(session);
    }

    @OnClose
    public void close(Session session) {
        log.info("Closing Roster Socket.");
        String uuid = rosterSessions.getRosterUserUUID(session.getId());
        rosterSessions.remove(session);
        if (uuid != null) controller.hideMe(uuid);
    }

    @OnError
    public void onError(Throwable e) {
        log.warn("Error with Roster Socket: {}", e);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        log.debug("    Received Roster Socket Message");
        RosterMessage rosterMessage = JSON.getObject(message, RosterMessage.class);
        controller.processMessage(rosterMessage, session);
    }

}

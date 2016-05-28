package io.xunil.web.presentation.websocket;

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

    public ChatSocket() {
        super();
        log.info("Constructing Chat Socket");
    }

    @OnOpen
    public void open(Session session) {
        log.info("Opening Socket.");
    }

    @OnClose
    public void close(Session session) {
        log.info("Closing Socket.");
    }

    @OnError
    public void onError(Throwable error) {
        log.warn("Error with Socket: {}", error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        log.debug("    Received Socket Message");
        // Just bounce it back for now
        session.getAsyncRemote().sendText(message);
    }
}

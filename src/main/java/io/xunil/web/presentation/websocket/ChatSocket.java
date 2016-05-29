package io.xunil.web.presentation.websocket;

import io.xunil.web.controller.ChatMessageController;
import io.xunil.web.memory.Sessions;
import io.xunil.web.presentation.model.ChatMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

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
        log.info("Opening Socket.");
        String sessionId = session.getId();
        sessions.addSession(sessionId, session);
        session.getAsyncRemote().sendText("Your current ID is: " + sessionId);
    }

    @OnClose
    public void close(Session session) {
        log.info("Closing Socket.");
        sessions.closeSession(session.getId());
    }

    @OnError
    public void onError(Throwable error) {
        log.warn("Error with Socket: {}", error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        log.debug("    Received Socket Message");
        ChatMessage chatMessage = null;
        try {
            // TODO: Refactor this into a util
            JAXBContext c = JAXBContext.newInstance(ChatMessage.class);
            Unmarshaller um = c.createUnmarshaller();
            um.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
            StreamSource ss = new StreamSource(new StringReader(message));
            chatMessage = um.unmarshal(ss, ChatMessage.class).getValue();
        }
        catch (JAXBException e) {
            log.error("JAXB Failed in Chat Socket: {}", e);
            return;
        }
        controller.processMessage(chatMessage);
    }
}

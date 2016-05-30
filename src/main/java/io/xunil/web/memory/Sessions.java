package io.xunil.web.memory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 5/28/16.
 *
 * This class stores and handles websocket sessions.
 *
 * There are two Maps of IDs because I want to keep the user's UUID session ID and
 *  the actual ID of the session (normally a small integer) abstracted from one another.
 */
public class Sessions {

    private static final Logger log = LogManager.getLogger(Sessions.class);
    private static Map<String, Session> sessions = new HashMap<>();
    private static Map<String, String> sessionTracker = new HashMap<>();
    private static Sessions self = new Sessions();

    private Sessions() {}

    public static Sessions getInstance() {
        return self;
    }

    public Session getSession(String id) {
        return sessions.get(id);
    }

    public void addSession(String id, Session session) {
        sessions.put(id, session);
        sessionTracker.put(session.getId(), id);
    }

    public void closeSession(String sessionId) {
        String id = sessionTracker.get(sessionId);
        if (id == null) return;
        Session session = sessions.get(id);
        if (session == null) return;
        if (session.isOpen()) {
            try {
                session.close();
            }
            catch (IOException e) {
                log.warn("Failed to close a websocket session: {}", e);
            }
            catch (IllegalStateException e) {
                log.debug("Socket was already closed before this message. Continuing as normal.");
            }
        }
        sessions.remove(id);
        sessionTracker.remove(sessionId);
    }
}

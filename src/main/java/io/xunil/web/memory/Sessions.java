package io.xunil.web.memory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 5/28/16.
 */
public class Sessions {

    private static final Logger log = LogManager.getLogger(Sessions.class);
    private static Map<String, Session> sessions = new HashMap<String, Session>();
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
    }

    public void closeSession(String id) {
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
    }
}

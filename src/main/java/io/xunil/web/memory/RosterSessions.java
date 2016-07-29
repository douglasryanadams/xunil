package io.xunil.web.memory;

import javax.websocket.Session;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 7/13/16.
 *
 * At the moment, this is just an abstraction later so that if I change the storage mechanism for these later
 * I have less to refactor elsewhere.
 */
public class RosterSessions {
    private Set<Session> rosterSockets;
    // Socket ID, User UUID
    private Map<String, String> rosterUsersById;
    private static RosterSessions self = new RosterSessions();

    private RosterSessions() {
        rosterSockets = new HashSet<>();
        rosterUsersById = new HashMap<>();
    }

    public static RosterSessions getInstance() { return self; }

    public void add(Session s) {
        rosterSockets.add(s);
    }

    public void setUUID(Session s, String userID) {
        rosterUsersById.put(s.getId(), userID);
    }

    public void remove(Session s) {
        rosterSockets.remove(s);
        rosterUsersById.remove(s.getId());
    }

    public Set<Session> getRosterSockets() {
        return rosterSockets;
    }

    public String getRosterUserUUID(String sessionId) {
        return rosterUsersById.get(sessionId);
    }
}

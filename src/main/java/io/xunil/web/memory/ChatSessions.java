package io.xunil.web.memory;

import io.xunil.web.exception.InconsistentDataException;
import io.xunil.web.memory.model.ChatSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 5/28/16.
 * <p>
 * This class stores and handles websocket sessions.
 * <p>
 * There are two Maps of IDs because I want to keep the user's UUID session ID and
 * the actual ID of the session (normally a small integer) abstracted from one another.
 */
public class ChatSessions {
    // Note: The storage mechanism here might make more sense as an in memory database in the future.

    private static final Logger log = LogManager.getLogger(ChatSessions.class);
    // UUID, ChatSession
    private static Map<String, ChatSession> sessions;
    // WebSocket Session ID, UUID
    private static Map<String, String> sessionTracker;
    private static List<String> visibleSessions;
    private static ChatSessions self = new ChatSessions();

    private ChatSessions() {
        sessions = new HashMap<>();
        sessionTracker = new HashMap<>();
        visibleSessions = new ArrayList<>();
    }

    public static ChatSessions getInstance() {
        return self;
    }

    public ChatSession getSession(String id) {
        return sessions.get(id);
    }

    public void addSession(ChatSession session) {
        sessions.put(session.getUuid(), session);
    }

    public void addWebsocketSessionId(String websocketSessionId, String chatSessionUUID) {
        sessionTracker.put(websocketSessionId, chatSessionUUID);
    }

    public void showSession(String id) {
        if (sessions.containsKey(id)) visibleSessions.add(id);
        else throw new InconsistentDataException("Attempted to make a non-existent session visible: " + id);
    }

    public void hideSession(String id) {
        visibleSessions.remove(id);
    }

    public void closeSession(String websocketSessionId) {
        String chatSessionUUID = sessionTracker.get(websocketSessionId);
        if (chatSessionUUID == null) return;
        ChatSession chatSession = sessions.get(chatSessionUUID);
        if (chatSession == null) return;
        Session session = chatSession.getSession();
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
        sessions.remove(chatSessionUUID);
        visibleSessions.remove(chatSessionUUID); // Might need defensive check first
        sessionTracker.remove(websocketSessionId);
    }

    public List<String> getVisibleSessions() {
        return visibleSessions;
    }
}

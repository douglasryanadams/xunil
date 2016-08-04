package io.xunil.web.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created on 7/31/16.
 */
public class ConnectionNegotiation {
    private ConnectionNegotiation self = new ConnectionNegotiation();
    // Requester:Recipient, Response from Target
    private static Map<String, Boolean> responseHash = new HashMap<>();
    // User, Unique ID
    private static Map<String, Integer> lockedUsers = new HashMap<>();
    
    private ConnectionNegotiation() {}
    
    private static String genKey(String requester, String recipient) {
        return requester + ":" + recipient;
    }
    
    public static void newConnection(String requester, String recipient) {
        responseHash.put(genKey(requester, recipient), null);
    }
    
    public static boolean waitingForAnswer(String requester, String recipient) {
        return null == responseHash.get(genKey(requester, recipient));
    }
    
    public static void answer(String requester, String recipient, Boolean answer) {
        responseHash.put(genKey(requester, recipient), answer);
    }
    
    public static Boolean answerConfirmed(String requester, String recipient) {
        return responseHash.remove(genKey(requester, recipient));
    }
    
    public static boolean lockUser(String requester) {
        Random r = new Random();
        Integer lockingId = r.nextInt();
        if (lockedUsers.containsKey(requester)) return false;
        lockedUsers.put(requester, lockingId);
        return lockingId.equals(lockedUsers.get(requester));
    }
    
    public static void unlockUser(String requester) {
        lockedUsers.remove(requester);
    }
    
}

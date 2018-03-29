package cc.jorgen.socket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.json.JSONObject;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Purpose of this class ...
 * <p>
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-03-28.
 */
@WebSocket
public class ChatSocket {

    private static Map<Session, String> userUsernameMap = new ConcurrentHashMap<>();
    private static int nextUserNumber = 1; // Assign

    @OnWebSocketConnect
    public void onConnect(Session session) throws Exception {
        String username = "User" + nextUserNumber++;
        System.out.println("Session open for " + username);
        userUsernameMap.put(session, username);
        broadcastMessage("Server", (username + " joined the chat"));
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        String username = userUsernameMap.get(session);
        System.out.println("Socket close for " + username);
        userUsernameMap.remove(session);
        broadcastMessage("Server", (username + " left the chat"));
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        String username = userUsernameMap.get(session);
        System.out.println("Socket message from " + username + ", msg: " + message);
        broadcastMessage(username, message);
    }

    @OnWebSocketError
    public void throwError(Throwable error) {
        System.out.println("Socket error");
        error.printStackTrace();
    }

    private static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                String username = userUsernameMap.get(session);
                System.out.println("  Broadcast message '' to " + username);

                session.getRemote().sendString(
                        new JSONObject()
                                .put("message", message)
                                .put("user", sender)
                                .put("time", new Date())
                                .put("userlist", userUsernameMap.values()).toString()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

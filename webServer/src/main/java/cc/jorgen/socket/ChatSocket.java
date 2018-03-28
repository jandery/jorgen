package cc.jorgen.socket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.json.JSONObject;

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
        System.out.println("Session open");
        String username = "User" + nextUserNumber++;
        userUsernameMap.put(session, username);
        broadcastMessage("Server", (username + " joined the chat"));
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("Session close");
        String username = userUsernameMap.get(session);
        userUsernameMap.remove(session);
        broadcastMessage("Server", (username + " left the chat"));
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        System.out.println("Session message: " + message);
        broadcastMessage(userUsernameMap.get(session), message);
    }

    private static void broadcastMessage(String sender, String message) {
        userUsernameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {
            try {
                session.getRemote().sendString(
                        new JSONObject()
                                .put("message", message)
                                .put("user", sender)
                                .put("userlist", userUsernameMap.values()).toString()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

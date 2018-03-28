package cc.jorgen.socket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * Purpose of this class ...
 * <p>
 * Created by Jorgen Andersson (jorgen@kollektiva.se) on 2018-03-28.
 */
@WebSocket
public class ChatSocket {

    @OnWebSocketConnect
    public void onConnect(Session session) throws Exception {
        System.out.println("Session open");

    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("Session close");

    }

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {
        System.out.println("Session message: " + message);

    }
}

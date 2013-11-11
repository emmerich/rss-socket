package org.emmerich.socket;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class EchoSocket {

    @OnWebSocketMessage
    public void onWebSocketText(Session session, String message) {
        session.getRemote().sendStringByFuture(message);
    }
}

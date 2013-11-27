package org.emmerich.webservice;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import org.eclipse.jetty.websocket.common.WebSocketSession;
import org.emmerich.session.IntervalSessionManager;
import org.emmerich.session.SessionManager;

@WebSocket
public class RSSSocket {

    private SessionManager sessionManager;

    public RSSSocket() {
        sessionManager = new IntervalSessionManager();
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        // Begin managing the session
        sessionManager.begin((WebSocketSession) session);
    }

    @OnWebSocketClose
    public void onWebSocketClose(Session session, int statusCode, String reason) {
        // End the session
        sessionManager.end((WebSocketSession) session);
    }

    @OnWebSocketError
    public void onWebSocketError(Session session, Throwable error) {
        sessionManager.end((WebSocketSession) session);
    }

}

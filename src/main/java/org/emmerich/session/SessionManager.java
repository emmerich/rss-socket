package org.emmerich.session;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.common.WebSocketSession;


public interface SessionManager {

    public void begin(WebSocketSession session);
    public void end(WebSocketSession session);

}

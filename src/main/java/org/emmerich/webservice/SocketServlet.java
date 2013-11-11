package org.emmerich.webservice;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import org.emmerich.socket.EchoSocket;

import javax.servlet.annotation.WebServlet;

@WebServlet(name = "SocketServlet", urlPatterns = { "/rss" })
public class SocketServlet extends WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.register(EchoSocket.class);
    }
}

package org.emmerich.session;

import org.eclipse.jetty.websocket.api.RemoteEndpoint;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.common.WebSocketSession;
import org.emmerich.provider.FeedProvider;
import org.emmerich.provider.JSONFeedProvider;
import sun.util.locale.InternalLocaleBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class IntervalSessionManager implements SessionManager {
    private FeedProvider feedProvider;
    private Map<Session, Timer> timers;

    public IntervalSessionManager() {
        timers = new HashMap<Session, Timer>();
        feedProvider = new JSONFeedProvider();
    }

    @Override
    public void begin(WebSocketSession session) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new FeedIntervalTask(feedProvider, getURL(session), session.getRemote()), 0, getInterval(session));
        timers.put(session, timer);
    }

    @Override
    public void end(WebSocketSession session) {
        timers.get(session).cancel();
    }

    private long getInterval(WebSocketSession session) {
        Map<String, List<String>> params = session.getUpgradeRequest().getParameterMap();
        List<String> interval = params.get("interval");

        if(interval.size() == 0) {
            throw new IllegalStateException("Need an interval");
        }

        return Long.parseLong(interval.get(0));
    }

    private URL getURL(WebSocketSession session) {
        URL url = null;

        try {
            url = new URL("http://www.scorespro.com/rss2/live-soccer.xml");
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return url;
    }

    private class FeedIntervalTask extends TimerTask {

        private FeedProvider feedProvider;
        private RemoteEndpoint remoteEndpoint;
        private URL url;

        public FeedIntervalTask(FeedProvider feedProvider, URL url, RemoteEndpoint remoteEndpoint) {
            this.feedProvider = feedProvider;
            this.remoteEndpoint = remoteEndpoint;
            this.url = url;
        }

        @Override
        public void run() {
            remoteEndpoint.sendStringByFuture(feedProvider.getFeed(url));
        }
    }
}

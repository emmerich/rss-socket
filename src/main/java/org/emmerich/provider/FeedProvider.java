package org.emmerich.provider;

import com.sun.syndication.feed.synd.SyndFeed;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: shall
 * Date: 13/11/13
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */
public interface FeedProvider {
    public String getFeed(URL url);
}

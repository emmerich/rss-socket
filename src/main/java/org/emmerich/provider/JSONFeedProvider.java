package org.emmerich.provider;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONFeedProvider implements FeedProvider {

    @Override
    public String getFeed(URL url) {
        String feed = "exception";

        try {
            SyndFeedInput input = new SyndFeedInput();
            feed = input.build(new XmlReader(url)).toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (FeedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return feed;
    }
}

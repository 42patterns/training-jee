package com.example.feeds;

import java.io.StringReader;

import javax.ejb.Stateless;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

@Stateless
public class FeedsService {

	public void parseFeed(String feedData) throws IllegalArgumentException, FeedException {
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new StringReader(feedData));
		
		System.out.println("Feed title: " + feed.getTitle());
	}
	
}

package com.example.feeds;

import static com.example.feeds.model.Feed.*;
import static com.example.feeds.model.Item.*;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.example.feeds.model.Feed;
import com.example.feeds.model.Item;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

@Stateless
public class FeedsService {

	@SuppressWarnings("unchecked")
	public Feed parseFeed(String feedData) {
		
		try {
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new StringReader(feedData));


		List<Item> items = new ArrayList<>();
		List<SyndEntryImpl> entries = feed.getEntries();
		for (SyndEntryImpl e: entries) {
			items.add(anItem()
				.withTitle(e.getTitle())
				.withLink(e.getLink())
				.withDate(e.getUpdatedDate())
				.withDescription(e.getDescription().getValue())
				.build());
		}
					
		return aFeed()
				.withTitle(feed.getTitle())
				.withLink(feed.getLink())
				.withItems(items)
				.build();
		} catch (IllegalArgumentException | FeedException e) {
			throw new ParsingException(e);
		}

	}

}

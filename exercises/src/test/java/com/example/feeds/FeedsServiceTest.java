package com.example.feeds;

import static com.example.feeds.model.Feed.*;
import static com.example.feeds.model.Item.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.example.feeds.model.Feed;
import com.sun.syndication.io.FeedException;

public class FeedsServiceTest {

	FeedsService service = new FeedsService();
	
	@Test
	public void shouldParseFeeds() throws IllegalArgumentException, FeedException {
		final String feeds = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
"<rss version=\"2.0\">" +
"<channel>" +
"  <title>W3Schools Home Page</title>" +
"  <link>http://www.w3schools.com</link>" +
"  <description>Free web building tutorials</description>" +
"  <item>" +
"    <title>RSS Tutorial</title>" +
"    <link>http://www.w3schools.com/rss</link>" +
"    <description>New RSS tutorial on W3Schools</description>" +
"  </item>" +
"  <item>" +
"    <title>XML Tutorial</title>" +
"    <link>http://www.w3schools.com/xml</link>" +
"    <description>New XML tutorial on W3Schools</description>" +
"  </item>" +
"</channel>" +
"</rss> ";
		
		Feed actual = service.parseFeed(feeds);
		Feed expected = aFeed()
				.withTitle("W3Schools Home Page")
				.withLink("http://www.w3schools.com")
				.addItems()
					.item(anItem().withTitle("RSS Tutorial").withLink("http://www.w3schools.com/rss")
							.withDescription("New RSS tutorial on W3Schools")
							.build())
					.item(anItem().withTitle("XML Tutorial").withLink("http://www.w3schools.com/xml")
							.withDescription("New XML tutorial on W3Schools")
							.build())
					.end()
				.build();

		assertEquals(expected, actual);		
	}
}

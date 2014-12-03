package com.example.feeds;

import static com.example.feeds.model.Feed.*;
import static com.example.feeds.model.Item.*;
import static org.junit.Assert.*;

import java.io.File;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.feeds.model.Feed;

@RunWith(Arquillian.class)
public class FeedsServiceIntegrationTest {

	@Deployment
	public static WebArchive deployment() {
		File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
				.resolve("rome:rome").withTransitivity()
				.asFile();
		
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addAsLibraries(libs)
				.addPackage(Feed.class.getPackage())
				.addClasses(ParsingException.class, FeedsParser.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	FeedsParser service;
	
	@Test
	public void shouldReturnFeedName() {
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

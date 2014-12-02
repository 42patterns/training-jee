package com.example.feeds;

import static org.junit.Assert.*;

import java.io.File;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.feeds.FeedsService;
import com.sun.syndication.io.FeedException;

@RunWith(Arquillian.class)
public class FeedsServiceIntegrationTest {

	@Deployment
	public static WebArchive deployment() {
		File[] libs = Maven.resolver().loadPomFromFile("pom.xml")
				.resolve("rome:rome").withTransitivity()
				.asFile();
		
		return ShrinkWrap.create(WebArchive.class)
				.addAsLibraries(libs)
				.addClass(FeedsService.class);				
	}

	@EJB
	FeedsService service;
	
	@Test
	public void shouldReturnFeedName() throws IllegalArgumentException, FeedException {
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
		
		String title = service.parseFeed(feeds);
		assertEquals("W3Schools Home Page", title);		

	}
	
}

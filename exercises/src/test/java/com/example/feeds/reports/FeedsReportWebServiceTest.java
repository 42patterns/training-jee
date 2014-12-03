package com.example.feeds.reports;

import java.io.File;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.feeds.FeedsParser;
import com.example.feeds.ParsingException;
import com.example.feeds.model.Feed;

@RunWith(Arquillian.class)
public class FeedsReportWebServiceTest {

	@Deployment
	public static WebArchive deployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(FeedsReportWebService.class.getPackage())
				.addAsManifestResource("META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	FeedsReportWebService service;

	@Test
	@Ignore
	public void shouldReturnFeedName() {
		service.reports();
	}

}

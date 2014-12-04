package com.example.feeds;

import static com.example.feeds.model.Feed.*;
import static com.example.feeds.model.Item.*;
import static org.apache.commons.lang3.RandomStringUtils.*;
import static org.junit.Assert.*;

import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.feeds.model.Feed;

@RunWith(Arquillian.class)
public class FeedsValidationTest {

	@Deployment
	public static WebArchive deployment() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackage(Feed.class.getPackage())
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Resource
	Validator validator;

	@Test
	public void emptyBeanNotValid() {
		Feed f = new Feed();

		Set<ConstraintViolation<Feed>> errors = validator.validate(f);
		assertEquals(3, errors.size());
	}

	@Test
	public void feedWithNoItemsNotValid() {
		Feed f = aFeed()
				.withTitle(randomAlphabetic(11))
				.withLink(randomAlphabetic(11))
				.build();
		
		Set<ConstraintViolation<Feed>> errors = validator.validate(f);
		assertEquals(1, errors.size());		
	}

	@Test
	public void validBean() {
		Feed f = aFeed()
				.withTitle(randomAlphabetic(11))
				.withLink(randomAlphabetic(11))
				.addItems()
				.item(anItem().withTitle(randomAlphabetic(11))
						.withLink(randomAlphabetic(11)).build()).end().build();

		Set<ConstraintViolation<Feed>> errors = validator.validate(f);
		assertTrue(errors.isEmpty());
	}

}

package com.example.feeds;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.feeds.model.Feed;

@Path("/")
public class FeedsResource {

	@EJB
	FeedsParser parser;
	
	@Inject
	Storage storage;
	
	@Resource
	Validator validator;
	
	@PostConstruct
	public void init() {
		System.out.println(storage);
	}
	
	@POST
	public Response addFeed(String body) {
		Feed feed = parser.parseFeed(body);
		
		Set<ConstraintViolation<Feed>> errors = validator.validate(feed);
		
		if (errors.isEmpty()) {
			storage.add(feed);
			return Response.status(Status.CREATED).build();
		} else {
			return Response.status(422).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		List<Feed> feeds = storage.get();
		GenericEntity<List<Feed>> entity = new GenericEntity<List<Feed>>(feeds) {};
		return Response.ok().entity(entity).build();
	}
	
}

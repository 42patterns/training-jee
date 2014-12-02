package com.example.feeds;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
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
	FeedsService parser;
	
	@Inject
	Storage storage;
	
	@PostConstruct
	public void init() {
		System.out.println(storage);
	}
	
	@POST
	public Response addFeed(String body) {
		parser.parseFeed(body);
		return Response.status(Status.CREATED).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get() {
		List<Feed> feeds = storage.get();
		GenericEntity<List<Feed>> entity = new GenericEntity<List<Feed>>(feeds) {};
		return Response.ok().entity(entity).build();
	}
	
}

package com.nordea.example;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

@Stateless
@Path("/todos")
public class Repository {

	@PersistenceContext
	EntityManager em;
	
	@GET
	public Response getAllTodos() {
		List<Todo> resultList = em.createNamedQuery(Todo.GET_ALL, Todo.class)
				.getResultList();

		GenericEntity<List<Todo>> entity = new GenericEntity<List<Todo>>(resultList) {};
		return Response.ok().entity(entity).build();
	}
	
	@GET
	@Path("/{id}")
	public Todo getTodo(@PathParam("id") long id) {
		return em.find(Todo.class, id);
	}
}

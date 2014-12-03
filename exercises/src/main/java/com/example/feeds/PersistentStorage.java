package com.example.feeds;

import java.util.List;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.feeds.model.Feed;

@Stateless
public class PersistentStorage implements Storage {

	@PersistenceContext
	EntityManager em;
	
	@Override
	@Interceptors(AuditInterceptor.class)
	public boolean add(Feed f) {
		em.persist(f);
		em.flush();
		
		return true;
	}

	@Override
	public List<Feed> get() {
		return em.createNamedQuery(Feed.GET_ALL, Feed.class).getResultList();
	}

}

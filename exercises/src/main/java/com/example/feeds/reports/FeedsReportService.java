package com.example.feeds.reports;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FeedsReportService {

	@PersistenceContext
	EntityManager em;
	
	@Asynchronous
	public void log(FeedsReportDTO dto) {
		em.persist(new FeedsReportEntity(dto));
		em.flush();
	}
	
}

package com.example.feeds.reports;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@WebService
@Stateless
public class FeedsReportWebService {

	@PersistenceContext
	EntityManager em;
	
	@Inject
	Event<ReportDTO> events;
	
	public ReportDTO[] reports() {
		//select feed_title, sum(items_cnt) as cnt from reports group by create_date, feed_title order by cnt;
		
		List<ReportDTO> report = em.createQuery("select new com.example.feeds.reports.ReportDTO(f.dto.title, sum(f.dto.itemsCount) as cnt) "
				+ "from FeedsReportEntity f "
				+ "group by dto.date, dto.title order by cnt desc")
				.getResultList();
		
		for (ReportDTO dto: report) {
			events.fire(dto);
		}
		return report.toArray(new ReportDTO[] {});
	}
}

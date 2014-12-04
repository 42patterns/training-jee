package com.example.feeds.reports;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@WebService
@Stateless
public class FeedsReportWebService {

	@PersistenceContext
	EntityManager em;
	
	public ReportDTO[] reports() {
		//select feed_title, sum(items_cnt) as cnt from reports group by create_date, feed_title order by cnt;
		
		List<ReportDTO> report = em.createQuery("select new com.example.feeds.reports.ReportDTO(f.dto.title, sum(f.dto.itemsCount) as cnt) "
				+ "from FeedsReportEntity f "
				+ "group by dto.date, dto.title order by cnt desc")
				.getResultList();
		
		return report.toArray(new ReportDTO[] {});
	}
}

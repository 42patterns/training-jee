package com.example.feeds.reports;

import static com.example.feeds.reports.db.tables.Reports.*;
import static org.jooq.impl.DSL.*;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import com.example.feeds.reports.db.tables.Reports;

@WebService
@Stateless
public class FeedsReportWebService {

	@Resource
	DataSource ds;
	
	EntityManager em;
	
	public ReportDTO[] reports() throws Exception {
		//select feed_title, sum(items_cnt) as cnt from reports group by create_date, feed_title order by cnt;

		Reports r = REPORTS;
		List<ReportDTO> report = DSL.using(ds, SQLDialect.MYSQL)
				.select(r.FEED_TITLE, sum(r.ITEMS_CNT).as("count"))
				.from(r)
				.groupBy(r.CREATE_DATE, r.FEED_TITLE)
				.orderBy(field("count").desc())
			.fetch().map(new ReportMapper());
		
		return report.toArray(new ReportDTO[] {});
	}
}

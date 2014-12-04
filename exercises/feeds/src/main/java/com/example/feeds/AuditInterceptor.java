package com.example.feeds;

import static com.example.feeds.reports.FeedsReportDTO.*;

import java.util.Date;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.example.feeds.model.Feed;
import com.example.feeds.reports.FeedsReportDTO;
import com.example.feeds.reports.FeedsReportService;

public class AuditInterceptor {

	@EJB
	FeedsReportService reports;
	
	@AroundInvoke
	public Object audit(InvocationContext ctx) throws Exception {

		Feed feed = (Feed) ctx.getParameters()[0];
		FeedsReportDTO dto = aReport()
				.withTitle(feed.getTitle())
				.withItemsCounts(feed.getItems().size())
				.withDate(new Date())
				.build();
		reports.log(dto);
		
		return ctx.proceed();
	}
	
}

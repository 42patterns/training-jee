package com.example.feeds.reports;

import static com.example.feeds.reports.db.tables.Reports.*;

import java.math.BigDecimal;

import org.jooq.Record2;
import org.jooq.RecordMapper;

import com.example.feeds.reports.db.tables.Reports;

public class ReportMapper implements RecordMapper<Record2<String, BigDecimal>, ReportDTO> {

	@Override
	public ReportDTO map(Record2<String, BigDecimal> record) {
		Reports r = REPORTS;
		return new ReportDTO(record.getValue(r.FEED_TITLE), record.getValue("count", Long.class));
	}

}

package com.example.feeds.reports;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class FeedsReportDTO implements Serializable {

	private String title;
	private int itemsCount;
	private Date date;

	public FeedsReportDTO() {}
	
	public FeedsReportDTO(String title, int itemsCount, Date date) {
		this.title = title;
		this.itemsCount = itemsCount;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public int getItemsCount() {
		return itemsCount;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "FeedsReportDTO [title=" + title + ", itemsCount=" + itemsCount
				+ ", date=" + date + "]";
	}

	public static ReportBuilder aReport() {
		return new ReportBuilder();
	}
	
	public static class ReportBuilder {
		private String title;
		private int itemsCount;
		private Date date;
		
		public ReportBuilder withTitle(String title) {
			this.title = title;
			return this;
		}
		
		public ReportBuilder withItemsCounts(int itemsCount) {
			this.itemsCount = itemsCount;
			return this;
		}
		
		public ReportBuilder withDate(Date date) {
			this.date = date;
			return this;
		}
		
		public FeedsReportDTO build() {
			return new FeedsReportDTO(title, itemsCount, date);
		}
	}
	
}

package com.example.feeds.reports;

public class ReportDTO {

	private String title;
	private Long count;

	public ReportDTO(String title, Long count) {
		super();
		this.title = title;
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ReportDTO [title=" + title + ", count=" + count + "]";
	}

}

package com.example.feeds.reports;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class FeedsReportEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="title", column=@Column(name="feed_title")),
		@AttributeOverride(name="itemsCount", column=@Column(name="items_cnt")),
		@AttributeOverride(name="date", column=@Column(name="create_date"))
	})	
	private FeedsReportDTO dto;
	
	public FeedsReportEntity() {}
	
	public FeedsReportEntity(FeedsReportDTO dto) {
		this.dto = dto;
	}

	public int getId() {
		return id;
	}

	public FeedsReportDTO getDto() {
		return dto;
	}

}

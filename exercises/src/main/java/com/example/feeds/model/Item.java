package com.example.feeds.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="items")
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String link;
	@Lob
	private String description;
	@Temporal(TemporalType.DATE)
	@Column(name="create_date")
	private Date date;

	public Item() {

	}

	public Item(String title, String link, String description, Date date) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

	@Override
	public String toString() {
		return "Item [title=" + title + ", link=" + link + ", description="
				+ description + ", date=" + date + "]";
	}

	public static ItemBuilder anItem() {
		return new ItemBuilder();
	}

	public static class ItemBuilder {
		private String title;
		private String link;
		private String description;
		private Date date;

		public ItemBuilder withTitle(String title) {
			this.title = title;
			return this;
		}

		public ItemBuilder withLink(String link) {
			this.link = link;
			return this;
		}

		public ItemBuilder withDescription(String description) {
			this.description = description;
			return this;
		}

		public ItemBuilder withDate(Date date) {
			this.date = date;
			return this;
		}

		public Item build() {
			return new Item(title, link, description, date);
		}
	}

}

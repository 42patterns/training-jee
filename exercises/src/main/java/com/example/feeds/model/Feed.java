package com.example.feeds.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Feed {

	private String title;
	private String link;
	private List<Item> items;

	public Feed() {

	}

	public Feed(String title, String link, List<Item> items) {
		this.title = title;
		this.link = link;
		this.items = items;
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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		Feed other = (Feed) obj;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
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
		return "Feed [title=" + title + ", link=" + link + ", \n"
				+ "items=" + items
				+ "]";
	}

	public static FeedBuilder aFeed() {
		return new FeedBuilder();
	}

	public static class FeedBuilder {
		private String title;
		private String link;
		private List<Item> items;

		public FeedBuilder withTitle(String title) {
			this.title = title;
			return this;
		}

		public FeedBuilder withLink(String link) {
			this.link = link;
			return this;
		}

		public FeedItemsBuilder addItems() {
			return new FeedItemsBuilder(this);
		}

		public FeedBuilder withItems(List<Item> items) {
			this.items = items;
			return this;
		}

		public Feed build() {
			return new Feed(title, link, items);
		}

		public static class FeedItemsBuilder {
			private List<Item> items;
			private FeedBuilder feedBuilder;

			public FeedItemsBuilder(FeedBuilder feedBuilder) {
				this.items = new ArrayList<>();
				this.feedBuilder = feedBuilder;
			}

			public FeedItemsBuilder item(Item object) {
				items.add(object);
				return this;
			}

			public FeedBuilder end() {
				return this.feedBuilder.withItems(items);
			}

		}
	}

}

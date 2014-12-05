package com.example.feeds;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.feeds.model.Feed;

@Named
@RequestScoped
public class RssForm implements Serializable {

	@EJB
	FeedsParser service;
	
	@Inject
	Storage storage;
	
	private String xml;

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
	
	public String send() {
		Feed feed = service.parseFeed(xml);
		storage.add(feed);
		return "list";
	}
	
	public List<Feed> getAll() {
		return storage.get();
	}

}

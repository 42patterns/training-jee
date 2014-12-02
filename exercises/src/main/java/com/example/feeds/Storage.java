package com.example.feeds;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.example.feeds.model.Feed;

@Singleton
@Startup
public class Storage {

	private List<Feed> elements = new ArrayList<Feed>();
	
	public void add(Feed f) {
		this.elements.add(f);
		System.out.println("Agregowano feedów: " + elements.size());
	}
	
	public List<Feed> get() {
		return new ArrayList<Feed>(elements);
	}
	
}

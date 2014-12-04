package com.example.feeds;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Alternative;

import com.example.feeds.model.Feed;

@Singleton
@Startup
@Alternative
public class MemoryStorage implements Storage {

	private List<Feed> elements = new ArrayList<Feed>();
	
	@Override
	public boolean add(Feed f) {
		boolean added = this.elements.add(f);
		System.out.println("Agregowano feedów: " + elements.size());
		
		return added;
	}
	
	@Override
	public List<Feed> get() {
		return new ArrayList<Feed>(elements);
	}
	
}

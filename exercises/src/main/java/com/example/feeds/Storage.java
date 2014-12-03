package com.example.feeds;

import java.util.List;

import com.example.feeds.model.Feed;

public interface Storage {

	public abstract boolean add(Feed f);

	public abstract List<Feed> get();

}
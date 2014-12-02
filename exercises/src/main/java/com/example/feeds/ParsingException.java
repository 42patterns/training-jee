package com.example.feeds;

import javax.ejb.ApplicationException;

@ApplicationException
public class ParsingException extends RuntimeException {

	private static final long serialVersionUID = 7552485064581716538L;

	public ParsingException(Exception e) {
		super(e);
	}

	
}

package com.example.feeds;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.feeds.model.Feed;

@WebServlet(urlPatterns = "/data")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = -5543582280281286830L;

	@EJB
	FeedsService service;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String raw_text = req.getParameter("feed-data");
		Feed f = service.parseFeed(raw_text);

		req.setAttribute("feed", f);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}	
	
}

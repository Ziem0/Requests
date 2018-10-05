package com.ziemo.requests.helpers;

import com.sun.net.httpserver.HttpExchange;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class URIHandler {

	public static Map<String, String> getRoute(HttpExchange exchange) {

		URI uri = exchange.getRequestURI();
		String path = uri.getPath();
		String query = uri.getQuery();

		return parseURI(path, query);
	}

	private static Map<String, String> parseURI(String path, String query) {

		Map<String, String> results = new HashMap<>();
		results.put("action", null);
		results.put("data", null);

		if (path.equalsIgnoreCase("/root")) {
			results.put("action", "root");
		} else if (path.equalsIgnoreCase("/request-counter")) {
			results.put("action", "counter");
		} else if (path.equalsIgnoreCase("/statistics")) {
			results.put("action", "statistics");
		} else if (path.equalsIgnoreCase("/json-generator")) {
			results.put("action", "generator");
			results.put("data", query);
		} else if (!path.equals("/root")) {
			results.put("action", "redirect");
		}

		return results;
	}
}

package com.ziemo.requests.controller;

import com.sun.net.httpserver.HttpExchange;
import com.ziemo.requests.dao.DaoCounters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public abstract class HttpHandler implements com.sun.net.httpserver.HttpHandler {

	DaoCounters dao = new DaoCounters();

	HttpExchange exchange;
	String response;

	String data;

	void sendResponse() throws IOException {
		exchange.sendResponseHeaders(200, response.length());

		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	void readInputs() throws IOException {
		InputStreamReader isr = new InputStreamReader(exchange.getRequestBody());
		BufferedReader br = new BufferedReader(isr);

		String values = br.readLine();
		Map<String, String> parsedInputs = parseInputs(values);

		// parse values f.e in fields
	}

	private Map<String, String> parseInputs(String values) {
		Map<String, String> results = new HashMap<>();

		String[] pairs = values.split("&");

		for (String pair : pairs) {
			String[] keyValue = pair.split("=");
			String value = URLDecoder.decode(keyValue[1]);
			results.put(keyValue[0], value);
		}

		return results;
	}


	void setExchange(HttpExchange exchange) {
		this.exchange = exchange;
	}

	String getMethod() {
		return exchange.getRequestMethod();
	}
}

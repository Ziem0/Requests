package com.ziemo.requests.controller;

import com.sun.net.httpserver.HttpExchange;
import com.ziemo.requests.helpers.URIHandler;
import com.ziemo.requests.model.Counters;
import com.ziemo.requests.view.WebDisplay;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class MainController extends HttpHandler {

	private String method;

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		setExchange(httpExchange);
		method = getMethod();

		String action = URIHandler.getRoute(httpExchange).get("action");
		data = URIHandler.getRoute(httpExchange).get("data");

		switch (action) {
			case "root":
				root();
				break;
			case "counter":
				counter();
				break;
			case "statistics":
				statistics();
				break;
			case "generator":
				generator();
				break;
			case "redirect":
				redirect();
				break;
		}
		sendResponse();
	}

	private void root() {
		response = WebDisplay.createRootPage();
	}

	private void counter() {
		Counters.add(method);
		response = WebDisplay.createCounterPage(method);
	}

	private void statistics() {
		dao.loadData();
		response = WebDisplay.createStatisticsPage();
	}

	private JSONObject generator() {
		Map<String, String> parsedData = parseData();

		JSONObject reqJson = new JSONObject();

		for (Map.Entry<String, String> entry : parsedData.entrySet()) {
			reqJson.put(entry.getKey(), entry.getValue());
		}

		String jsonToString = reqJson.toString();
		response = WebDisplay.createGeneratorPage(jsonToString);

		return reqJson;
	}

	private Map<String, String> parseData() {
		Map<String, String> results = new HashMap<>();

		String[] pairs = data.split("&");

		for (String pair : pairs) {
			String[] keyValue = pair.split("=");
			String value = URLDecoder.decode(keyValue[1]);
			results.put(keyValue[0], value);
		}

		return results;
	}

	private void redirect() throws IOException {
		exchange.getResponseHeaders().set("Location", "/root");
		exchange.sendResponseHeaders(302, -1);
	}
}

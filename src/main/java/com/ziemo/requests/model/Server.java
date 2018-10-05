package com.ziemo.requests.model;

import com.sun.net.httpserver.HttpServer;
import com.ziemo.requests.controller.MainController;
import com.ziemo.requests.controller.Static;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {

	private final int port;
	private HttpServer server;

	public Server(int port) {
		this.port = port;
	}

	public void setup() throws IOException {
		server = HttpServer.create(new InetSocketAddress(port), 0);

		server.createContext("/root", new MainController());
		server.createContext("/request-counter", new MainController());
		server.createContext("/statistics", new MainController());
		server.createContext("/json-generator", new MainController());
		server.createContext("/static", new Static());

		server.setExecutor(null);
	}

	public void runServer() {
		server.start();
	}
}

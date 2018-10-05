package com.ziemo.requests;

import com.ziemo.requests.model.Server;

import java.io.IOException;

public class App {

	public static void main(String[] args) {
		Server server = new Server(8000);

		try {
			server.setup();
			server.runServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

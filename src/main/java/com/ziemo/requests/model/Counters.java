package com.ziemo.requests.model;

import com.ziemo.requests.dao.DaoCounters;

import java.util.HashMap;
import java.util.Map;

public class Counters {

	public static Map<String, Integer> data = new HashMap<>();
	private static DaoCounters dao = new DaoCounters();

	public static void add(String method) {

		if (!data.containsKey(method)) {
			data.put(method, 1);
			dao.addData(method);
		} else {
			int value = data.get(method) + 1;
			data.put(method, value);
			dao.updateData(method, String.valueOf(value));
		}
	}

}

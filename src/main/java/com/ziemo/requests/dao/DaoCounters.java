package com.ziemo.requests.dao;

public class DaoCounters extends DaoHandler {

	public void loadData() {
		String query = "select * from counter;";

		handleQuery(query, null);
	}

	public void updateData(String method, String amount) {
		String command = "update counter set amount=? where method=?;";

		handleQuery(command, new String[]{amount, method});
	}

	public void addData(String method) {
		String command = "insert into counter(method) values(?);";

		handleQuery(command, new String[]{method});
	}
}

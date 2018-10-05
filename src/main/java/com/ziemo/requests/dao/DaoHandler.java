package com.ziemo.requests.dao;

import com.ziemo.requests.model.Counters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

class DaoHandler {

	private Connection connection = null;
	private PreparedStatement statement = null;
	private ResultSet resultSet = null;
	private Map<String, Integer> results;

	void handleQuery(String query, String[] inputs) {

		connection = ConnectDB.getConnection();
		results = Counters.data;

		try {
			buildQuery(query, inputs);

			if (query.startsWith("select")) {
				resultSet = statement.getResultSet();
				saveResults();
			} else {
				statement.executeUpdate();
			}

			close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void close() throws SQLException {
		statement.close();

		if (resultSet != null) {
			resultSet.close();
		}
	}

	private void saveResults() throws SQLException {

		while (resultSet.next()) {
			results.put(resultSet.getString(1), Integer.valueOf(resultSet.getString(2)));
		}

	}

	private void buildQuery(String query, String[] inputs) throws SQLException {

		statement = connection.prepareStatement(query);

		int counter = 1;

		if (inputs != null) {
			for (String s : inputs) {
				statement.setString(counter, s);
				counter++;
			}
		}
	}
}

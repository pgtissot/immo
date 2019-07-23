package com.edu.realestate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractDaoJDBC {

	private static Connection connection;

	protected Connection getConnection() {
		if (connection == null) {
			String params = "?serverTimezone=UTC";
			String url = "jdbc:mysql://localhost:3307/realestate" + params;
			String name = "root";
			String password = "";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url, name, password);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

	protected void disconnect() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

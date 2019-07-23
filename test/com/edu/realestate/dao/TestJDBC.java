package com.edu.realestate.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		String params = "?serverTimezone=UTC";
		String url = "jdbc:mysql://localhost:3307/realestate" + params;
		String name = "root";
		String password = "";

		Connection connect = DriverManager.getConnection(url, name, password);

		Statement st = connect.createStatement();
		ResultSet res = st.executeQuery("SELECT * from city ORDER BY name");
		while (res.next())
			System.out.println(res.getString("name"));

		st = connect.createStatement();
		res = st.executeQuery("SELECT * from house");
		while (res.next())
			System.out.println(res.getString("id"));

		connect.close();

	}
}

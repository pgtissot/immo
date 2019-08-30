package com.edu.realestate.services;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public class AbstractT {

	protected void cleanInsert()
			throws MalformedURLException, ClassNotFoundException, SQLException, DatabaseUnitException {
				IDataSet dataset = loadXmlDataSet("src/test/resources/datasets/user_service/clean-insert.xml");
				IDatabaseConnection cn = null;
				try {
					cn = getConnection();
					DatabaseOperation.CLEAN_INSERT.execute(cn, dataset);
				} finally {
					cn.close();
				}
			}

	protected IDatabaseConnection getConnection() throws SQLException, ClassNotFoundException, DatabaseUnitException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3307/realestate_test?useSSL=false&serverTimezone=UTC";
	
		Connection cn = DriverManager.getConnection(url, "scott", "tiger");
		
		return new DatabaseConnection(cn);
	}

	protected IDataSet loadXmlDataSet(String filename) throws MalformedURLException, DataSetException {
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		return builder.build(new File(filename));
	}

	protected ReplacementDataSet getReplacement(IDataSet dataset) {
		ReplacementDataSet replacement = new ReplacementDataSet(dataset);
		replacement.addReplacementObject("[null]", null);
		return replacement;
	}
	
}
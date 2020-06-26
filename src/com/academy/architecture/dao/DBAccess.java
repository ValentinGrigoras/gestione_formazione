package com.academy.architecture.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBAccess implements DAOConstants {

	private static Connection conn;

	public synchronized static Connection getConnection() throws DAOException, ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("properties/config.properties");

		Properties p = new Properties();
		p.load(input);

		try {
			Class.forName(p.getProperty("jdbcDriver"));
			conn = DriverManager.getConnection(p.getProperty("jdbcURL"), p.getProperty("jdbcUsername"),
					p.getProperty("jdbcPassword"));
			conn.setAutoCommit(false);

			return conn;
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public static void closeConnection() throws DAOException {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}
}

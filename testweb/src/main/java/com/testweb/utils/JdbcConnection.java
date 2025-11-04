package com.testweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

	public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
	public static final String USER = "postgres";
	public static final String PSWD = "password@001";

	
	public JdbcConnection() {
		
	}

	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(DB_URL, USER, PSWD);
			// System.out.println("connection is ready..."+conn);
			return conn;
		} catch (Exception e) {
			System.err.println("Connection error:" + e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}

	public static void closeConn(Connection connection) {
		try {
			if (connection != null)
				connection.close();
			// System.out.println("Connection closed.");
		} catch (SQLException e) {
			System.out.println("Error while closing connection:" + e.getMessage());
		}
	}

}

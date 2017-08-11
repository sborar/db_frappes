package com.db.connector;

import java.sql.*;

public class Connector {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_grad_cs_1917?useSSL=true", "root", "root");

		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
}

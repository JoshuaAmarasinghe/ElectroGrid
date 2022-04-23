package util;

import java.sql.*;

public class DBConnection {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://127.0.0.1:3306/";
	private static String database = "ElectroGrid";
	private static String username = "root";
	private static String password = "";

    // common method to connect to DB
	public static Connection connect() {
		Connection connection = null;
		
		try {
			Class.forName(driver);
			//DB connection details
			connection = DriverManager.getConnection(url + database, username, password);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return connection;
	}
}

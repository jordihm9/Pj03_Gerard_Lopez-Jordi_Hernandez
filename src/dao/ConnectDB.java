package dao;

/* Jordi Hernandez i Gerard Lopez */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private static ConnectDB myConnection = null;
	private static Connection con = null;
	
	private ConnectDB() {
		final String driver		= "com.mysql.jdbc.Driver";
		final String dbName		= "Pj03_Gerard-Jordi";
		final String host		= "localhost";
		final String url		= "jdbc:mysql://" + host + "/" + dbName;
		final String user		= "root";
		final String password	= "";
		try {
			// load the driver
			Class.forName(driver);
			// connect to the database
			con = DriverManager.getConnection(url, user, password);
		}
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection connect() throws SQLException {
		// check if the connection is not active
		if (con == null || con.isClosed()) {
			myConnection = new ConnectDB();
		}
		return con;
	}
}

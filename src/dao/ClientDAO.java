package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Client;

public class ClientDAO {
	/**
	 * Find a client in the database by ID
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static Client selectById(int id) throws SQLException {
		Connection con = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			// connect to database
			con = ConnectDB.connect();
			// create the prepared statement
			String query = "SELECT c.`id` as 'client_id', c.`nif`, c.`name`, c.`lastname`, c.`address`, c.`town`"
					+ " FROM `clients` c"
					+ " WHERE c.`id` = ?;";
			select = con.prepareStatement(query);
			// set the value for the prepared statement
			select.setInt(1, id);
			
			// execute the statement
			rs = select.executeQuery();
			
			// check if there is a result
			if (rs.next()) {
				return new Client(rs);
			}
		}
		finally {
			con.close();
			select.close();
			rs.close();
		}
		
		return null;
	}
	
	/**
	 * Fins a client in the database by NIF
	 * @param nif
	 * @return
	 * @throws SQLException
	 */
	public static Client selectByNif(String nif) throws SQLException {
		Connection con = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			// connect to database
			con = ConnectDB.connect();
			// create the prepared statement
			String query = "SELECT c.`id` AS `client_id`, c.`nif`, c.`name`, c.`lastname`, c.`address`, c.`town`"
					+ " FROM `clients` c"
					+ " WHERE c.`nif` = ?;";
			select = con.prepareStatement(query);
			// set the value for the prepared statement
			select.setString(1, nif);
			
			// execute the statement
			rs = select.executeQuery();
			
			// check if there is a result
			if (rs.next()) {
				return new Client(rs);
			}
		}
		finally {
			con.close();
			select.close();
			rs.close();
		}
		
		return null;
	}
	
	public static void insert(Client client) throws SQLException {
		Connection con = null;
		PreparedStatement insert = null;
		
		try {
			// connect to DB
			con = ConnectDB.connect();
			// create the prepared statement
			String query = "INSERT INTO `clients` (`nif`, `name`, `lastname`, `address`, `town`)"
					+ " VALUES (?, ?, ?, ?, ?);";
			insert = con.prepareStatement(query);
			// set the values for the prepared statement
			insert.setString(1, client.getNif());
			insert.setString(2, client.getName());
			insert.setString(3, client.getLastname());
			insert.setString(4, client.getAddress());
			insert.setString(5, client.getTown());
			// execute the statement
			insert.executeUpdate();
		}
		finally {
			con.close();
		}
	}
}

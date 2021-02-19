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
					+ " FROM `clients` c WHERE `id` = ?";
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
					+ "FROM `clients` WHERE `nif` = ?";
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
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Invoice;

public class InvoiceDAO {
	/**
	 * Select all invoices from the database
	 * @return List of invoices
	 * @throws SQLException
	 */
	public static ArrayList<Invoice> selectAll() throws SQLException {
		ArrayList<Invoice> invoicesList = new ArrayList<>();
		Connection con = null;
		Statement select = null;
		ResultSet rs = null;
		
		try {
			// connect to DB
			con = ConnectDB.connect();
			// create the statement
			select = con.createStatement();
			// set the statement
			String query = "SELECT * FROM `invoices`";
			// execute the statement
			rs = select.executeQuery(query);
			
			// add every result to the list
			while (rs.next()) {
				invoicesList.add(new Invoice(rs));
			}
		}
		finally {
			con.close();
			select.close();
			rs.close();
		}
		
		return invoicesList;
	}
	
	/**
	 * Find an invoice in the database by ID
	 * @param id
	 * @return Invoice found
	 * @throws SQLException
	 */
	public static Invoice selectById(int id) throws SQLException {
		Connection con = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			// connect to DB
			con = ConnectDB.connect();
			// create the prepared statement
			String query = "SELECT * FROM `invoices` WHERE `id` = ?";
			select = (PreparedStatement) con.prepareStatement(query);
			// set the value for the prepared statement
			select.setInt(1, id);
			// execute the statement
			rs = select.executeQuery();
			
			// check if there is a result
			if (rs.next()) {
				return new Invoice(rs);
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

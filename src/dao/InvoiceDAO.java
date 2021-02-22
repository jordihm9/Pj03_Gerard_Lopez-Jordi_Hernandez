package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Client;
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
			String query = "SELECT i.`id` AS 'invoice_id', i.`date`, i.`paid`, i.`taxable_base`, i.`iva`, i.`iva_import`, i.`discount`, i.`discount_import`, i.`total`,"
					+ " c.`id` AS 'client_id', c.`nif`, c.`name`, c.`lastname`, c.`address`, c.`town`"
					+ " FROM `invoices` i"
					+ " INNER JOIN `clients` c ON c.`id` = i.`client_id`;";
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
			String query = "SELECT i.`id` AS 'invoice_id', i.`date`, i.`paid`, i.`taxable_base`, i.`iva`, i.`iva_import`, i.`discount`, i.`discount_import`, i.`total`,"
					+ " c.`id` AS 'client_id', c.`nif`, c.`name`, c.`lastname`, c.`address`, c.`town`"
					+ " FROM `invoices` i"
					+ " INNER JOIN `clients` c ON c.`id` = i.`client_id`"
					+ " WHERE i.`id` = ?;";
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
	
	/**
	 * Insert a new invoice
	 * @param invoice
	 * @throws SQLException
	 */
	public static void insert(Invoice invoice) throws SQLException {
		Connection con = null;
		PreparedStatement insert = null;
		
		try {
			// connect to DB
			con = ConnectDB.connect();
			// create prepared statement
			String query = "INSERT INTO `invoices` (`date`, `paid`, `taxable_base`, `iva`, `iva_import`, `discount`, `discount_import`, `total`, `client_id`)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			insert = con.prepareStatement(query);
			// set values for the prepared statement
			insert.setDate(1, invoice.getDate());
			insert.setBoolean(2, invoice.isPaid());
			insert.setDouble(3, invoice.getTaxableBase());
			insert.setFloat(4,  invoice.getIva());
			insert.setFloat(5, invoice.getIvaImport());
			insert.setFloat(6, invoice.getDiscount());
			insert.setFloat(7, invoice.getDiscountImport());
			insert.setFloat(8, invoice.getTotal());
			insert.setInt(9, invoice.getClient().getId());
			// execute the insert
			insert.executeUpdate();
		}
		finally {
			con.close();
			insert.close();
		}
	}
	
	/**
	 * Delete an invoice by ID
	 * @param id
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public static boolean delete(int id) throws SQLException {
		boolean deleteOk = false;
		Connection con = null;
		PreparedStatement delete = null;
		
		try {
			// connect to DB
			con = ConnectDB.connect();
			// create the prepared statement
			String query = "DELETE FROM `invoices` WHERE `id` = ?;";
			delete = con.prepareStatement(query);
			// set the value for the prepared statement
			delete.setInt(1, id);
			// execute the statement
			delete.executeUpdate();
			// invoices details are deleted automatically when the invoice has been deleted
			deleteOk = true;
		}
		finally {
			con.close();
			delete.close();
			return deleteOk;
		}
	}
}

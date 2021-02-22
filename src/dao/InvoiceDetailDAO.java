package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Invoice;
import models.InvoiceDetail;

public class InvoiceDetailDAO {
	/**
	 * Select all the details from an invoice
	 * @param id key from the invoice
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<InvoiceDetail> selectByInvoiceId(int id) throws SQLException {
		ArrayList<InvoiceDetail> invoiceDetailList = new ArrayList<>();
		Connection con = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			// connect to DB
			con = ConnectDB.connect();
			// create the prepared statement
			String query = "SELECT id.`id` AS 'invoice_detail_id', id.`line_number`, id.`total_articles`, id.`line_price`,"
					+ " i.`id` AS 'invoice_id', i.`date`, i.`paid`, i.`taxable_base`, i.`iva`, i.`iva_import`, i.`discount`, i.`discount_import`, i.`total`,"
					+ " a.`id` AS 'article_id', a.`code`, a.`name`, a.`price`,"
					+ " c.`id` AS 'client_id', c.`nif`, c.`name`, c.`lastname`, c.`address`, c.`town`"
					+ " FROM `invoices_details` id"
					+ " INNER JOIN `invoices` i ON i.`id` = id.`invoice_id`"
					+ " INNER JOIN `articles` a ON a.`id` = id.`article_id`"
					+ " INNER JOIN `clients` c ON c.`id` = 	i.`client_id`"
					+ " WHERE i.`id` = ?;";
			select = (PreparedStatement) con.prepareStatement(query);
			// set the value for the prepared statement
			select.setInt(1, id);
			
			// execute the statement
			rs = select.executeQuery();
			
			// add every result to the list
			while (rs.next()) {
				invoiceDetailList.add(new InvoiceDetail(rs));
			}
		}
		finally {
			con.close();
			select.close();
			rs.close();
		}
		
		return invoiceDetailList;
	}
	
	/**
	 * Delete all the invoices details related to an invoice (by ID)
	 * @param id key from the invoice
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("finally")
	public static void deleteByInvoiceId(int id) throws SQLException {
		Connection con = null;
		PreparedStatement delete = null;
		
		try {
			// connect to DB
			con = ConnectDB.connect();
			// create the prepared statement
			String query = "DELETE FROM `invoices_details` WHERE `invoice_id` = ?;";
			delete = con.prepareStatement(query);
			// set values for prepared statement
			delete.setInt(1, id);
			// execute the statement
			delete.executeUpdate();
		}
		finally {
			con.close();
			delete.close();
		}
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Article;

public class ArticleDAO {
	/**
	 * Find an article in the database by id
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static Article selectById(int id) throws SQLException {
		Connection con = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			// connect to DB
			con = ConnectDB.connect();
			// create the prepared statement
			String query = "SELECT * FROM `articles` WHERE `id` = ?";
			select = (PreparedStatement) con.prepareStatement(query);
			// set the value for the prepared statement
			select.setInt(1, id);
			// execute the statement
			rs = select.executeQuery();
			
			// check if there is a result
			if (rs.next()) {
				return new Article(rs);
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
	 * Find an article in the database by code
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public static Article selectByCode(String code) throws SQLException {
		Connection con = null;
		PreparedStatement select = null;
		ResultSet rs = null;
		
		try {
			// connect to DB
			con = ConnectDB.connect();
			// create the prepared statement
			String query = "SELECT * FROM `articles` WHERE `code` = ?";
			select = (PreparedStatement) con.prepareStatement(query);
			// set the value for the prepared statement
			select.setString(1, code);
			// execute the statement
			rs = select.executeQuery();
			
			// check if there is a result
			if (rs.next()) {
				return new Article(rs);
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

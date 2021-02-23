package controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InvoiceDetailDAO;
import models.InvoiceDetail;

/**
 * Servlet implementation class SaveInvoiceDetails
 */
@WebServlet("/invoice-detail/save")
public class SaveInvoiceDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveInvoiceDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setStatus(405); // "Method not allowed"
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// get data from request and create an invoice detail
			InvoiceDetail detail = new InvoiceDetail(request);
			
			// save to the database
			InvoiceDetailDAO.insert(detail);		
		}
		catch (SQLException e) {
			// send server error status code (500)
			response.setStatus(500);
			
			e.printStackTrace();
		}
	}
}

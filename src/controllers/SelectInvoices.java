package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.InvoiceDAO;
import models.Invoice;

/**
 * Servlet implementation class SendInvoices
 */
@WebServlet("/invoices/select")
public class SelectInvoices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectInvoices() {
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
			// select all invoices from database
			ArrayList<Invoice> list = InvoiceDAO.selectAll();
			
			// check the list is not empty
			if (list.size() >= 1) {
				// parse ArrayList to JSON (string)
				String json = new Gson().toJson(list);
				response.getWriter().write(json);
				
				// set response attributes
				response.setStatus(200); // 200: all OK
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			} else {
				response.setStatus(204); // "No Content" server not returning any content
			}
		} catch (SQLException e) {
			// send server error status code (500)
			response.setStatus(500);

			e.printStackTrace();
		}
	}

}

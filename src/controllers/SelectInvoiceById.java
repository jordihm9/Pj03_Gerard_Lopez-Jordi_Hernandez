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
import dao.InvoiceDetailDAO;
import models.Invoice;
import models.InvoiceDetail;

/**
 * Servlet implementation class SelectInvoiceById
 */
@WebServlet("/invoice/select")
public class SelectInvoiceById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectInvoiceById() {
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
			int id = Integer.parseInt(request.getParameter("id"));
			
			// select specific invoice from database
			Invoice invoice = InvoiceDAO.selectById(id);
			
			// select the list of invoice details
			ArrayList<InvoiceDetail> details = InvoiceDetailDAO.selectByInvoiceId(id);
			
			// check if there is an invoice
			if (invoice != null) {
				// parse invoice to json and list of details to json
				String invoiceJSON = new Gson().toJson(invoice);
				String detailsJSON = new Gson().toJson(details);
				
				String json = "{\"invoice\":"+ invoiceJSON +",\"details\":"+ detailsJSON +"}";
				
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

package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDAO;
import dao.InvoiceDAO;
import dao.InvoiceDetailDAO;
import models.Client;
import models.Invoice;

/**
 * Servlet implementation class SaveInvoice
 */
@WebServlet("/invoice/save")
public class SaveInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveInvoice() {
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
			// get data sent in the request and create an invoice and client objects
			Invoice invoice = new Invoice(request);
			Client client = new Client(request);
			
			// check if client exists
			if (ClientDAO.selectByNif(client.getNif()) == null) {
				// create client
				ClientDAO.insert(client);
			}
			// get the client from the DB
			client = ClientDAO.selectByNif(client.getNif());
			
			// set invoice client
			invoice.setClient(client);
			
			// check invoice exists
			if (InvoiceDAO.selectById(invoice.getId()) == null) {
				// create the new invoice
				InvoiceDAO.insert(invoice);
			} else {
				// update the invoice
				// InvoiceDetailDAO.update(invoice);
				
				// delete all details related with this invoice
				InvoiceDetailDAO.deleteByInvoiceId(invoice.getId());
			}
			// get the invoice from the DB
			invoice = InvoiceDAO.selectById(invoice.getId());
			
			// insert all the details
			// InvoiceDetailsDAO
		}
		catch (SQLException e) {
			// send server error status code (500)
			response.setStatus(500);
			
			e.printStackTrace();
		}
	}

}

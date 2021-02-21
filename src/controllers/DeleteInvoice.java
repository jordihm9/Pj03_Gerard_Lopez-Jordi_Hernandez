package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.InvoiceDAO;

/**
 * Servlet implementation class DeleteInvoice
 */
@WebServlet("/invoice/delete")
public class DeleteInvoice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteInvoice() {
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
			// get id sent in the request
			int id = Integer.parseInt(request.getParameter("id"));
			
			// delete from the database
			// if delete went ok, return 200, if not return 404
			response.setStatus(InvoiceDAO.delete(id) ? 200 : 404);
		}
		catch (SQLException e) {
			// send server error status code (500)
			response.setStatus(500);

			e.printStackTrace();
		}
	}

}

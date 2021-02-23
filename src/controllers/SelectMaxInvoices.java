package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.InvoiceDAO;

/**
 * Servlet implementation class SelectMaxInvoices
 */
@WebServlet("/invoice/selectMax")
public class SelectMaxInvoices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectMaxInvoices() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Select Max Id form invoices
		try {
			int maxId = InvoiceDAO.selectMaxID();
			
			if(maxId != 0) {
				System.out.print(maxId);
				
				String maxIdJson = new Gson().toJson(maxId);
				
				response.getWriter().write(maxIdJson);
				
				// set response attributes
				response.setStatus(200); // 200: all OK
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			} else {
				response.setStatus(204);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

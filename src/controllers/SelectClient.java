package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ArticleDAO;
import dao.ClientDAO;
import models.Article;
import models.Client;

/**
 * Servlet implementation class SelectClient
 */
@WebServlet("/client/select")
public class SelectClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectClient() {
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
			String nif = request.getParameter("nif");
			
			// find in DB the code
			Client client = ClientDAO.selectByNif(nif);
			
			// check if an article was found
			if (client != null) {
				// parse article to json
				String json = new Gson().toJson(client);
				response.getWriter().write(json);
				
				// set response attributes
				response.setStatus(200); // 200: all OK
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
			} else {
				response.setStatus(204); // "No Content" server not returning any content
			}
		}
		catch (SQLException e) {
			// send server error status code (500)
			response.setStatus(500);
			
			e.printStackTrace();
		}
	}

}

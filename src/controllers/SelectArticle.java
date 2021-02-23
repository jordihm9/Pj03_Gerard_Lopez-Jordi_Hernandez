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
import models.Article;

/**
 * Servlet implementation class SelectArticle
 */
@WebServlet("/article/select")
public class SelectArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectArticle() {
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
			String code = request.getParameter("code");
			
			// find in DB the code
			Article article = ArticleDAO.selectByCode(code);
			
			// check if an article was found
			if (article != null) {
				// parse article to json
				String json = new Gson().toJson(article);
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

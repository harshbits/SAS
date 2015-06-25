package sas.servelets.news;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sas.data.storage.objects.News;
import sas.data.storage.objects.User;
import sas.database.NewsDatabase;
import sas.database.UserDatabase;
/**
 * Servlet implementation class UploadService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/NewsUploadService" })
@MultipartConfig
public class NewsUploadService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendError(506, "Not Supported");
	}

	/**
	 * @see HttpServlet#doNews(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			User user = (User) request.getSession().getAttribute("user");
			
			if (UserDatabase.checkIfUserHasLoggedIn(user) == false)
				throw new Exception("Not logged in!!");	
			
			if (user.getCategory() != 2 && user.getCategory() != 3) {
				
				throw new Exception("User not authorised to use this page");
			}
			
			
			News news = new News();
			
			news.setNewsTitle(request.getParameter("title"));
			news.setPostedBy(user.getUserid());
			news.setNewsContent(request.getParameter("newscontent"));
			news.setNewsLink(request.getParameter("newslink"));
			news.setCategory(request.getParameter("category"));
			
			Boolean isSaved = NewsDatabase.saveNewsdetails(news);		
			
			if (isSaved == true)
				request.setAttribute("isSaved", "saved");
			else
				request.setAttribute("isSaved", "not saved");
			
			request.getRequestDispatcher("news.jsp").forward(request, response);
			
		}
		catch (Exception e) {
			try {
				request.setAttribute("errorMessage", e.getMessage());
				request.getRequestDispatcher("internal error.jsp").forward(request, response);
			} catch (Exception f) {
				System.out.println(f.getMessage() + " " + e.getMessage());
			}
		}
	}
}

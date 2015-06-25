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
 * Servlet implementation class BookDownloadService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/NewsDetailService" })
@MultipartConfig
public class NewsDetailService extends HttpServlet {
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
	try {
			
			User user = (User) request.getSession().getAttribute("user");
			
			if (UserDatabase.checkIfUserHasLoggedIn(user) == false)
				throw new Exception("Not logged in!!");	
		
			News news = NewsDatabase.getNewsData(Long.parseLong(request.getParameter("newsid")));

			if (news == null)
				throw new Exception("invalid!!");
			
			request.setAttribute("content", news.getNewsContent());
			request.setAttribute("title", news.getNewsTitle());
			request.setAttribute("link", news.getNewsLink());
			request.setAttribute("postedby",news.getPostedBy() );
			request.setAttribute("datetime", news.getDateTime());
			
			request.getRequestDispatcher("news-in-detail.jsp").forward(request, response);
			
			
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

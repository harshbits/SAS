package sas.servelets.news;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sas.data.storage.objects.User;
import sas.database.NewsDatabase;
import sas.database.UserDatabase;

/**
 * Servlet implementation class NewsService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/NewsService" })
public class NewsService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {
			
			User user = (User) request.getSession().getAttribute("user");
			
			if (UserDatabase.checkIfUserHasLoggedIn(user) == false)
				throw new Exception("Not logged in!!");	
			
			
			request.setAttribute("results", NewsDatabase.getNewsDetails());

			request.getRequestDispatcher("news.jsp").forward(request, response);
			
		}	catch (Exception e) {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		doPost(request, response);
	}
}

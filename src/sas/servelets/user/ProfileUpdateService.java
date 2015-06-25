package sas.servelets.user;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sas.data.storage.objects.User;
import sas.database.UserDatabase;

/**
 * Servlet implementation class ProfileUpdateService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ProfileUpdateService" })
public class ProfileUpdateService extends HttpServlet {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			User user = new User();			
			
			user.setUserid(request.getParameter("userid"));
			user.setPassword(request.getParameter("password"));
			user.setUsername(request.getParameter("name"));
			user.setCategory(Integer.decode(request.getParameter("category")));
			user.setDepartment(request.getParameter("department"));
			
			if (UserDatabase.CheckforExistingUserId(user) == true)
			{
				UserDatabase.UpdateUserDetails(user);		
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("index.jsp");
			}
			else
				throw new Exception("User does not Exists!!!");
		}
		catch(Exception e) {
			try {
				request.setAttribute("errorMessage", e.getMessage());
				request.getRequestDispatcher("internal error.jsp").forward(request, response);
			} catch (Exception f) {
				System.out.println(f.getMessage() + " " + e.getMessage());
			}
		}		
	}

}

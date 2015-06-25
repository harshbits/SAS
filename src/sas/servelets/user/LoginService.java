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
 * Servlet implementation class LoginServelet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LoginService" })
public class LoginService extends HttpServlet {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			
			User user = new User();
			user.setUserid(request.getParameter("userid"));
			user.setPassword(request.getParameter("password"));
					
			UserDatabase.GetUserDetails(user);
			
			if (user.getUsername() != null)	
			{
				HttpSession session = request.getSession(true);
				
				User ExistingUser = (User) session.getAttribute("user");
				
				if (ExistingUser != null && ExistingUser.getUserid() != null)
					throw new Exception("User Session is active!!");
				
				session.setAttribute("user", user);
			}
			
			response.sendRedirect("index.jsp?message=Invalid credentials..Logon Rejected");
			
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

package sas.servelets.user;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/LogoutService" })
public class LogoutService extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

    private void performLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp?message=Logout Successfull..Thank you");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		performLogout(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		performLogout(request, response);
	}

}

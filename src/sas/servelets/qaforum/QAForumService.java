package sas.servelets.qaforum;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sas.data.storage.objects.QAForum;
import sas.data.storage.objects.User;
import sas.database.QAForumDatabase;
import sas.database.UserDatabase;
/**
 * Servlet implementation class DiscussionForumService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/QAForumService" })
public class QAForumService extends HttpServlet {
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
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) {
	try {
			
			User user = (User) request.getSession().getAttribute("user");
			
			if (UserDatabase.checkIfUserHasLoggedIn(user) == false)
				throw new Exception("Not logged in!!");	
			
			switch (user.getCategory()) {
			case 1:
				QAForum qaForum = new QAForum();
				qaForum.setPostedBy(user.getUserid());
				request.setAttribute("topics", QAForumDatabase.getQAForumdetails(qaForum));
				break;
				
			case 2:
				request.setAttribute("topics", QAForumDatabase.getQAForumdetails());
				break;

			default:
				request.setAttribute("topics", null);
				break;
			}

			request.getRequestDispatcher("qaForum.jsp").forward(request, response);
			
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

}

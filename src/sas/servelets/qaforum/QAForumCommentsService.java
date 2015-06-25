package sas.servelets.qaforum;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sas.data.storage.objects.QAForum;
import sas.data.storage.objects.QAForumReplies;
import sas.data.storage.objects.User;
import sas.database.QAForumCommentsDatabase;
import sas.database.QAForumDatabase;
import sas.database.UserDatabase;

/**
 * Servlet implementation class QAForumCommentService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/QAForumCommentsService" })
public class QAForumCommentsService extends HttpServlet {
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

			QAForum  qaForum = new QAForum();
			QAForumReplies qaForumComments = new QAForumReplies();
			
			qaForum.setQuestionId(Long.parseLong(request.getParameter("questionid")));
			qaForumComments.setQuestionId(Long.parseLong(request.getParameter("questionid")));
			
			if (user.getCategory() != 2 && user.getCategory() != 3 && !QAForumDatabase.getQAForumTopicdetails(qaForum).getPostedBy().equals(user.getUserid())) {
				
				throw new Exception("User not authorized to view this page");
			}
			
			request.setAttribute("topic", QAForumDatabase.getQAForumTopicdetails(qaForum));
			request.setAttribute("replies", QAForumCommentsDatabase.getQAForumCommentsdetails(qaForumComments));
			request.getRequestDispatcher("qaThread.jsp").forward(request, response);
			
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

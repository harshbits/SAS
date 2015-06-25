package sas.servelets.jobs;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sas.data.storage.objects.Job;
import sas.data.storage.objects.User;
import sas.database.JobsDatabase;
import sas.database.UserDatabase;
/**
 * Servlet implementation class UploadService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/JobUploadService" })
@MultipartConfig
public class JobUploadService extends HttpServlet {
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
	 * @see HttpServlet#doBook(HttpServletRequest request, HttpServletResponse response)
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
			
			Job job = new Job();
			
			job.setTitle(request.getParameter("job_title"));
			job.setSkill_set(request.getParameter("skill_set"));			
			job.setJob_function(request.getParameter("job_function"));
			job.setExperience(request.getParameter("job_experience"));
			job.setEmployement_type(request.getParameter("job_employement_type"));				
			job.setPostedBy(user.getUserid());
		
			Boolean isSaved = JobsDatabase.saveJobdetails(job);	
			
			if (isSaved == true)
				request.setAttribute("isSaved", "saved");
			else
				request.setAttribute("isSaved", "not saved");
			
			request.getRequestDispatcher("job.jsp").forward(request, response);
			
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

package sas.servelets.videos;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import sas.data.storage.objects.User;
import sas.data.storage.objects.Video;
import sas.database.UserDatabase;
import sas.database.VideosDatabase;
/**
 * Servlet implementation class UploadService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/VideoUploadService" })
@MultipartConfig
public class VideoUploadService extends HttpServlet {
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
	 * @see HttpServlet#dovideo(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			User user = (User) request.getSession().getAttribute("user");
			
			if (UserDatabase.checkIfUserHasLoggedIn(user) == false)
				throw new Exception("Not logged in!!");	

			Video video = new Video();
			
			video.setUserid(user.getUserid());
			video.setTitle(request.getParameter("title"));
			
			video.setCategory(request.getParameter("category"));	
			video.setCourseId(request.getParameter("courseno"));
			
			video.setMimetype(request.getPart("file").getContentType());
			
			try {
				
				Part filePart = request.getPart("file");
				
				

				if (filePart != null) {
					
					video.setFileName(filePart.getSubmittedFileName());	
					video.setInputStream(filePart.getInputStream());
		        }
				else
					throw new Exception("No file Received!!!!");
			}
			catch (Exception e) {
				throw e;
			}
			
			Boolean isSaved = VideosDatabase.saveVideodetails(video);		
			
			if (isSaved == true)
				request.setAttribute("isSaved", "saved");
			else
				request.setAttribute("isSaved", "not saved...Please contact support..");
			
			request.getRequestDispatcher("video.jsp").forward(request, response);
			
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

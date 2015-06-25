package sas.servelets.videos;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sas.data.storage.objects.User;
import sas.data.storage.objects.Video;
import sas.database.UserDatabase;
import sas.database.VideosDatabase;

/**
 * Servlet implementation class BookDownloadService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/VideoRetrievalService" })
@MultipartConfig
public class VideoRetrievalService extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	try {
			
			User user = (User) request.getSession().getAttribute("user");
			
			if (UserDatabase.checkIfUserHasLoggedIn(user) == false) {
				
				throw new Exception("Not logged in!!");	
			}
		
			Video video = VideosDatabase.getVideoData(Long.parseLong(request.getParameter("videoid")));


			if (video == null) {

				throw new Exception("invalid!!");
			}

            // sets MIME type for the file download

            if (video.getMimetype() == null) {  
            	
            	video.setMimetype("video/mp4");
            }    

            video.setResponseProperties(response); // set content properties and header attributes for the response    
			video.writeToStream(response.getOutputStream()); // writes the file to the client   
			
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

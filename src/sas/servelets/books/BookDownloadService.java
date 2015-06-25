package sas.servelets.books;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sas.data.storage.objects.Book;
import sas.data.storage.objects.User;
import sas.database.BooksDatabase;
import sas.database.UserDatabase;

/**
 * Servlet implementation class BookDownloadService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/BookDownloadService" })
@MultipartConfig
public class BookDownloadService extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
																								IOException {
		try {
			
			User user = (User) request.getSession().getAttribute("user");
			
			if (UserDatabase.checkIfUserHasLoggedIn(user) == false)
				throw new Exception("Not logged in!!");	
		
			Book book = BooksDatabase.getBookData(Long.parseLong(request.getParameter("bookid")));

			if (book == null)
				throw new Exception("invalid!!");
			
            // sets default  MIME type for the file download
            
            if (book.getMimetype() == null) { 
            	
                book.setMimetype("application/octet-stream");
            }    

            
            book.setResponseProperties(response); // set content properties and header attributes for the response
            book.writeToStream(response.getOutputStream()); // writes the file to the client     
			
			
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

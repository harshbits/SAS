package sas.servelets.books;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import sas.data.storage.objects.Book;
import sas.data.storage.objects.User;
import sas.database.BooksDatabase;
import sas.database.UserDatabase;
/**
 * Servlet implementation class UploadService
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/BookUploadService" })
@MultipartConfig
public class BookUploadService extends HttpServlet {
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

			Book book = new Book();
			
			book.setUserid(user.getUserid());
			book.setTitle(request.getParameter("title"));
			
			book.setCategory(request.getParameter("category"));
			book.setAuthor(request.getParameter("author"));
			book.setEdition(request.getParameter("edition"));
		
			
			book.setMimetype(request.getPart("file").getContentType());
			
			try {
				Part filePart = request.getPart("file");
				
				

				if (filePart != null) {
					
					book.setFileName(filePart.getSubmittedFileName());	
					book.setInputStream(filePart.getInputStream());
		        }
				else
					throw new Exception("No file Received!!!!");
			}
			catch (Exception e) {
				throw e;
			}
		
			Boolean isSaved = BooksDatabase.saveBookdetails(book);		
			
			if (isSaved == true)
				request.setAttribute("isSaved", "saved");
			else
				request.setAttribute("isSaved", "not saved");
			
			request.getRequestDispatcher("ebook.jsp").forward(request, response);
			
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

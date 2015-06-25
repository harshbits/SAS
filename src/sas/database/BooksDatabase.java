/**
 * 
 */
package sas.database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import javax.naming.NamingException;

import sas.data.storage.objects.Book;

/**
 * @author VASUDEV
 *
 * Contains functions that are used for all SQL operation on Book sas.database. 
 */


public class BooksDatabase extends MasterDatabase {

	
	public BooksDatabase(String jndiname) throws NamingException, SQLException
	{
			super(jndiname);
	}
	
	public static Boolean saveBookdetails(Book book) throws SQLException, Exception
	{
		
		Connection connection = null;
		PreparedStatement statement = null;
		Boolean IsInsertSuccessfull = false;
		
		try {
			int count = 0;
			String sqlQuery = "Insert into EBOOK_SAS (TITLE, CATEGORY, AUTHOR, EDITION, BOOK, USERID, FILE_NAME, MIMETYPE) "
					         +" values (?, ?, ?, ?, ?, ?, ?, ?)"; 
			BooksDatabase booksDatabase = new BooksDatabase("dbuser");
			connection = booksDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getCategory());
			statement.setString(3, book.getAuthor());
			statement.setString(4, book.getEdition());
			statement.setBlob(5, book.getInputStream());
			statement.setString(6, book.getUserid());
			statement.setString(7, book.getFileName());
			statement.setString(8, book.getMimetype());
			
			try {
				count = statement.executeUpdate();
			}
			catch(SQLException e) {
				throw new Exception(e.getSQLState() + " " + e.getMessage());
			}
			
		    IsInsertSuccessfull = (count > 0);
		}
		catch (Exception e) {
				throw e;
		}
		finally {
			try {	statement.close();  } catch (Exception e) {}
			try {	connection.close(); } catch (Exception e) {}
		}
		return IsInsertSuccessfull;
	}
	
	public static Queue<Book> getBookdetailsForUser(String userid) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<Book> queue = new LinkedList <Book> ();	
		
		try {		
			String sqlQuery = "SELECT BOOK_ID, TITLE, CATEGORY, AUTHOR, EDITION, USERID, DATE_TIME, FILE_NAME"
			                 + " FROM EBOOK_SAS WHERE UserId = ? ORDER BY DATE_TIME Desc";
			
			BooksDatabase booksDatabase = new BooksDatabase("dbuser");
			connection = booksDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			

			if (userid == null)
				userid = "";
			
			statement.setString(1, userid);
			
			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				Book book = new Book();
				book.setBookId(resultSet.getLong("Book_ID"));
				book.setUserid(resultSet.getString("UserId"));
				book.setTitle(resultSet.getString("title"));
				book.setCreatedDate(resultSet.getTimestamp("date_time"));
				book.setCategory(resultSet.getString("Category"));
				book.setAuthor(resultSet.getString("Author"));
				book.setTitle(resultSet.getString("Title"));
				book.setEdition(resultSet.getString("Edition"));
				book.setFileName(resultSet.getString("file_name"));
				book.setMimetype(resultSet.getString("mimetype"));
				queue.add(book);
			}
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			try {	resultSet.close();  } catch (Exception e) {}
			try {	statement.close();  } catch (Exception e) {}
			try {	connection.close(); } catch (Exception e) {}
		}
		
		return queue;
	}
	
	
	
	public static Book getBookData(long BookId) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;	
		Book book = new Book();
		try {		
			String sqlQuery = "SELECT BOOK, FILE_NAME, MIMETYPE FROM EBOOK_SAS WHERE BOOK_ID = ?";
			
			BooksDatabase booksDatabase = new BooksDatabase("dbuser");
			connection = booksDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setLong(1, BookId);
			
			resultSet = statement.executeQuery();		
			
			if (resultSet.next())
			{
				
				book.setFileName(resultSet.getString("FILE_NAME"));
				Blob blob = resultSet.getBlob("BOOK");
				book.setMimetype(resultSet.getString("mimetype"));
				book.setLength(blob.length());
				book.setInputStream(blob.getBinaryStream());
			}
		}
		catch (SQLException|NamingException  e) {
			throw e;
		}
		finally {
			try {	resultSet.close();  } catch (Exception e) {}
			try {	statement.close();  } catch (Exception e) {}
			try {	connection.close(); } catch (Exception e) {}
		}
		
		return book;
	}
	
	public static Queue<Book> searchBooks(String search) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<Book> queue = new LinkedList <Book> ();
		
		try {
			
			String sqlQuery = "SELECT BOOK_ID, TITLE, CATEGORY, AUTHOR, EDITION, USERID, DATE_TIME, FILE_NAME"
	                 + " FROM EBOOK_SAS WHERE LOWER(Title) LIKE ? OR LOWER(Author) LIKE ? OR LOWER(EDITION) LIKE ? or LOWER(CATEGORY) LIKE ?"
					+" or LOWER(USERID) LIKE ?  or LOWER(FILE_NAME) LIKE ?"
					+ " ORDER BY DATE_TIME Desc";
			
			BooksDatabase booksDatabase = new BooksDatabase("dbuser");
			connection = booksDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			

			
			if (search == null)
				search = "";

			search = search.toLowerCase();
			
			statement.setString(1, "%" + search + "%");
			statement.setString(2, "%" + search + "%");
			statement.setString(3, "%" + search + "%");
			statement.setString(4, "%" + search + "%");
			statement.setString(5, "%" + search + "%");
			statement.setString(6, "%" + search + "%");
			
			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				Book book = new Book();
				book.setBookId(resultSet.getLong("Book_ID"));
				book.setUserid(resultSet.getString("UserId"));
				book.setTitle(resultSet.getString("title"));
				book.setCreatedDate(resultSet.getTimestamp("date_time"));
				book.setCategory(resultSet.getString("Category"));
				book.setAuthor(resultSet.getString("Author"));
				book.setTitle(resultSet.getString("Title"));
				book.setEdition(resultSet.getString("Edition"));
				book.setFileName(resultSet.getString("file_name"));
				queue.add(book);
			}
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			try {	resultSet.close();  } catch (Exception e) {}
			try {	statement.close();  } catch (Exception e) {}
			try {	connection.close(); } catch (Exception e) {}
		}
		
		return queue;
	}
}

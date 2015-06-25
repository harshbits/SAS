package sas.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import javax.naming.NamingException;

import sas.data.storage.objects.News;

public class NewsDatabase extends MasterDatabase {

	public NewsDatabase(String jndiname) throws NamingException, SQLException {
		super(jndiname);
		
	}
	
	public static Boolean saveNewsdetails(News news) throws SQLException, Exception
	{
		
		Connection connection = null;
		PreparedStatement statement = null;
		Boolean IsInsertSuccessfull = false;
		
		try {
			int count = 0;
			String sqlQuery = "Insert into NEWS_SAS (NEWS_TITLE, NEWS_LINK, POSTED_BY, CATEGORY, NEWS_CONTENT) "
					         +" values (?, ?, ?, ?, ?)"; 
			
			NewsDatabase newsDatabase = new NewsDatabase("dbuser");
			connection = newsDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setString(1, news.getNewsTitle());
			statement.setString(2, news.getNewsLink());
			statement.setString(3, news.getPostedBy());
			statement.setString(4, news.getCategory());
			statement.setString(5, news.getNewsContent());
			
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
	
	public static Queue<News> getNewsDetails()throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<News> queue = new LinkedList <News> ();	
		
		try {		
			String sqlQuery = "SELECT *"
			                 + " FROM NEWS_SAS ORDER BY DATE_TIME Desc";
			
			NewsDatabase newsDatabase = new NewsDatabase("dbuser");
			connection = newsDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			

			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				News news=new News();
				news.setNewsId(resultSet.getLong("NEWS_ID"));
				news.setNewsTitle(resultSet.getString("NEWS_TITLE"));
				news.setCategory(resultSet.getString("CATEGORY"));
				news.setNewsContent(resultSet.getString("NEWS_CONTENT"));
				news.setNewsLink(resultSet.getString("NEWS_LINK"));
				news.setPostedBy(resultSet.getString("POSTED_BY"));
				news.setDateTime(resultSet.getTimestamp("DATE_TIME"));
				queue.add(news);
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
	
	public static Queue<News> getNewsdetailsForUser(String userid) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<News> queue = new LinkedList <News> ();	
		
		try {		
			String sqlQuery = "SELECT NEWS_ID, NEWS_TITLE, DATE_TIME, CATEGORY, POSTED_BY, NEWS_CONTENT"
			                 + " FROM NEWS_SAS WHERE POSTED_BY = ? ORDER BY DATE_TIME Desc";
			
			NewsDatabase newsDatabase = new NewsDatabase("dbuser");
			connection = newsDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			

			if (userid == null)
				userid = "";
			
			statement.setString(1, userid);
			
			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				News news=new News();
				news.setNewsId(resultSet.getLong("NEWS_ID"));
				news.setNewsTitle(resultSet.getString("NEWS_TITLE"));
				news.setCategory(resultSet.getString("CATEGORY"));
				news.setNewsContent(resultSet.getString("NEWS_CONTENT"));
				news.setNewsLink(resultSet.getString("NEWS_LINK"));
				news.setPostedBy(resultSet.getString("POSTED_BY"));
				news.setDateTime(resultSet.getTimestamp("DATE_TIME"));
				queue.add(news);
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
	
	
	public static News getNewsData(long NewsId) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;	
		News news = new News();
		try {		
			String sqlQuery = "SELECT NEWS_TITLE, NEWS_CONTENT, NEWS_LINK, POSTED_BY, DATE_TIME FROM NEWS_SAS WHERE NEWS_ID = ?";
			
			NewsDatabase newsDatabase = new NewsDatabase("dbuser");
			connection = newsDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setLong(1, NewsId);
			
			resultSet = statement.executeQuery();		
			
			if (resultSet.next())
			{
				
				news.setNewsTitle(resultSet.getString("NEWS_TITLE"));
				news.setNewsContent(resultSet.getString("NEWS_CONTENT"));
				news.setNewsLink(resultSet.getString("NEWS_LINK"));
				news.setDateTime(resultSet.getTimestamp("DATE_TIME"));
				news.setPostedBy(resultSet.getString("POSTED_BY"));
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
		
		return news;
	}
	
	/*public static Queue<News> searchNews(String search) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<Book> queue = new LinkedList <Book> ();
		
		try {
			
			String sqlQuery = "SELECT BOOK_ID, TITLE, CATEGORY, AUTHOR, EDITION, USERID, DATE_TIME, FILE_NAME"
	                 + " FROM EBOOK_SAS WHERE Title LIKE ? OR Author LIKE ? OR EDITION LIKE ?"
					+ " ORDER BY DATE_TIME Desc";
			
			BooksDatabase booksDatabase = new BooksDatabase("dbuser");
			connection = booksDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			

			
			if (search == null)
				search = "";

			
			statement.setString(1, "%" + search + "%");
			statement.setString(2, "%" + search + "%");
			statement.setString(3, "%" + search + "%");
			
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
*/
}

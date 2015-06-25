/**
 * 
 */
package sas.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

import javax.naming.NamingException;

import sas.data.storage.objects.QAForum;

/**
 * @author VASUDEV
 *
 * Contains functions that are used for all SQL operation on Book sas.database. 
 */


public class QAForumDatabase extends MasterDatabase {

	
	public QAForumDatabase(String jndiname) throws NamingException, SQLException
	{
			super(jndiname);
	}
	
	public static Boolean saveQAForumdetails(QAForum qaForum) throws SQLException, Exception
	{
		
		Connection connection = null;
		PreparedStatement statement = null;
		Boolean IsInsertSuccessfull = false;
		
		try {
			int count = 0;
			String sqlQuery = "Insert into QA_FORUM_SAS (TITLE, QUESTION, POSTED_BY) "
					         +" values (?, ?, ?)"; 
			QAForumDatabase qaForumDatabase = new QAForumDatabase("dbuser");
			connection = qaForumDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setString(1, qaForum.getTitle());
			statement.setString(2, qaForum.getQuestion());
			statement.setString(3, qaForum.getPostedBy());
			
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
	
	public static Queue<QAForum> getQAForumdetails() throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<QAForum> queue = new LinkedList <QAForum> ();	
		
		try {		
			String sqlQuery = "SELECT QUESTION_ID, TITLE, QUESTION, POSTED_BY, DATE_TIME"
			                 + " FROM QA_FORUM_SAS ORDER BY DATE_TIME Desc";
			
			QAForumDatabase qaForumDatabase = new QAForumDatabase("dbuser");
			connection = qaForumDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				QAForum qaForum = new QAForum();
				qaForum.setQuestionId(resultSet.getLong("QUESTION_ID"));
				qaForum.setQuestion(resultSet.getString("Question"));
				qaForum.setPostedBy(resultSet.getString("Posted_By"));
				qaForum.setTitle(resultSet.getString("Title"));
				qaForum.setDateTime(resultSet.getTimestamp("Date_Time"));
			
				queue.add(qaForum);
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
	
	public static Queue<QAForum> getQAForumdetails(QAForum searchInput) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<QAForum> queue = new LinkedList <QAForum> ();	
		
		try {		
			String sqlQuery = "SELECT QUESTION_ID, TITLE, QUESTION, POSTED_BY, DATE_TIME"
			                 + " FROM QA_FORUM_SAS WHERE POSTED_BY = ? ORDER BY DATE_TIME Desc";
			
			QAForumDatabase qaForumDatabase = new QAForumDatabase("dbuser");
			connection = qaForumDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setString(1, searchInput.getPostedBy());
			
			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				QAForum qaForum = new QAForum();
				qaForum.setQuestionId(resultSet.getLong("QUESTION_ID"));
				qaForum.setQuestion(resultSet.getString("Question"));
				qaForum.setPostedBy(resultSet.getString("Posted_By"));
				qaForum.setTitle(resultSet.getString("Title"));
				qaForum.setDateTime(resultSet.getTimestamp("Date_Time"));
			
				queue.add(qaForum);
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
	
	public static QAForum getQAForumTopicdetails(QAForum searchInput) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		QAForum qaForum = new QAForum();	
		
		try {		
			String sqlQuery = "SELECT QUESTION_ID, TITLE, QUESTION, POSTED_BY, DATE_TIME"
			                 + " FROM QA_FORUM_SAS WHERE QUESTION_ID = ? or POSTED_BY = ? ORDER BY DATE_TIME Desc";
			
			QAForumDatabase qaForumDatabase = new QAForumDatabase("dbuser");
			connection = qaForumDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setLong(1, searchInput.getQuestionId());
			statement.setString(2, searchInput.getPostedBy());
			
			resultSet = statement.executeQuery();		
			
			if (resultSet.next())
			{
				qaForum.setQuestionId(resultSet.getLong("Question_ID"));
				qaForum.setQuestion(resultSet.getString("Question"));
				qaForum.setPostedBy(resultSet.getString("Posted_By"));
				qaForum.setTitle(resultSet.getString("Title"));
				qaForum.setDateTime(resultSet.getTimestamp("Date_Time"));
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
		
		return qaForum;
	}
}

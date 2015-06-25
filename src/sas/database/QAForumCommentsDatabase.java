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

import sas.data.storage.objects.QAForumReplies;

/**
 * @author VASUDEV
 *
 * Contains functions that are used for all SQL operation on Book sas.database. 
 */


public class QAForumCommentsDatabase extends MasterDatabase {

	
	public QAForumCommentsDatabase(String jndiname) throws NamingException, SQLException
	{
			super(jndiname);
	}
	
	public static Boolean saveQAForumCommentsdetails(QAForumReplies discussionForumComments) throws SQLException, Exception
	{
		
		Connection connection = null;
		PreparedStatement statement = null;
		Boolean IsInsertSuccessfull = false;
		
		try {
			int count = 0;
			String sqlQuery = "Insert into QA_FORUM_REPLIES_SAS (QUESTION_ID, REPLY, POSTED_BY) "
					         +" values (?, ?, ?)"; 
			QAForumCommentsDatabase discussionForumCommentsDatabase = new QAForumCommentsDatabase("dbuser");
			connection = discussionForumCommentsDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setLong(1, discussionForumComments.getQuestionId());
			statement.setString(2, discussionForumComments.getReply());
			statement.setString(3, discussionForumComments.getPostedBy());
			
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

	
	public static Queue<QAForumReplies> getQAForumCommentsdetails(QAForumReplies searchInput) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<QAForumReplies> queue = new LinkedList <QAForumReplies> ();	
		
		try {		
			String sqlQuery = "SELECT QUESTION_ID, REPLY, POSTED_BY, DATE_TIME"
			                 + " FROM QA_FORUM_REPLIES_SAS WHERE QUESTION_ID = ? ORDER BY DATE_TIME Desc";
			
			QAForumCommentsDatabase discussionForumCommentsDatabase = new QAForumCommentsDatabase("dbuser");
			connection = discussionForumCommentsDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			

			statement.setLong(1, searchInput.getQuestionId());
			
			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				QAForumReplies discussionForumComments = new QAForumReplies();
				discussionForumComments.setQuestionId(resultSet.getLong("Question_Id"));
				discussionForumComments.setPostedBy(resultSet.getString("Posted_By"));
				discussionForumComments.setReply(resultSet.getString("Reply"));
				discussionForumComments.setDateTime(resultSet.getTimestamp("Date_Time"));
			
				queue.add(discussionForumComments);
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

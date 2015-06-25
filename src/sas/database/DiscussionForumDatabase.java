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

import sas.data.storage.objects.DiscussionForum;

/**
 * @author VASUDEV
 *
 * Contains functions that are used for all SQL operation on Book sas.database. 
 */


public class DiscussionForumDatabase extends MasterDatabase {

	
	public DiscussionForumDatabase(String jndiname) throws NamingException, SQLException
	{
			super(jndiname);
	}
	
	public static Boolean saveDiscussionForumdetails(DiscussionForum discussionForum) throws SQLException, Exception
	{
		
		Connection connection = null;
		PreparedStatement statement = null;
		Boolean IsInsertSuccessfull = false;
		
		try {
			int count = 0;
			String sqlQuery = "Insert into DISCUSSION_FORUM_SAS (TITLE, DESCRIPTION, POSTED_BY) "
					         +" values (?, ?, ?)"; 
			DiscussionForumDatabase discussionForumDatabase = new DiscussionForumDatabase("dbuser");
			connection = discussionForumDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setString(1, discussionForum.getTitle());
			statement.setString(2, discussionForum.getDescription());
			statement.setString(3, discussionForum.getPostedBy());
			
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
	
	public static Queue<DiscussionForum> getDiscussionForumdetails() throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<DiscussionForum> queue = new LinkedList <DiscussionForum> ();	
		
		try {		
			String sqlQuery = "SELECT DISCUSSION_ID, TITLE, DESCRIPTION, POSTED_BY, DATE_TIME"
			                 + " FROM DISCUSSION_FORUM_SAS ORDER BY DATE_TIME Desc";
			
			DiscussionForumDatabase discussionForumDatabase = new DiscussionForumDatabase("dbuser");
			connection = discussionForumDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				DiscussionForum discussionForum = new DiscussionForum();
				discussionForum.setDiscussionId(resultSet.getLong("DISCUSSION_ID"));
				discussionForum.setDescription(resultSet.getString("Description"));
				discussionForum.setPostedBy(resultSet.getString("Posted_By"));
				discussionForum.setTitle(resultSet.getString("Title"));
				discussionForum.setDateTime(resultSet.getTimestamp("Date_Time"));
			
				queue.add(discussionForum);
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
	
	public static DiscussionForum getDiscussionForumTopicdetails(DiscussionForum searchInput) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		DiscussionForum discussionForum = new DiscussionForum();	
		
		try {		
			String sqlQuery = "SELECT DISCUSSION_ID, TITLE, DESCRIPTION, POSTED_BY, DATE_TIME"
			                 + " FROM DISCUSSION_FORUM_SAS WHERE DISCUSSION_ID = ? ORDER BY DATE_TIME Desc";
			
			DiscussionForumDatabase discussionForumDatabase = new DiscussionForumDatabase("dbuser");
			connection = discussionForumDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			

			statement.setLong(1, searchInput.getDiscussionId());
			
			resultSet = statement.executeQuery();		
			
			if (resultSet.next())
			{
				discussionForum.setDiscussionId(resultSet.getLong("Discussion_ID"));
				discussionForum.setDescription(resultSet.getString("Description"));
				discussionForum.setPostedBy(resultSet.getString("Posted_By"));
				discussionForum.setTitle(resultSet.getString("Title"));
				discussionForum.setDateTime(resultSet.getTimestamp("Date_Time"));
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
		
		return discussionForum;
	}
}

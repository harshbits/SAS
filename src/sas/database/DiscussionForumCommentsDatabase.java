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

import sas.data.storage.objects.DiscussionForumComments;

/**
 * @author VASUDEV
 *
 * Contains functions that are used for all SQL operation on Book sas.database. 
 */


public class DiscussionForumCommentsDatabase extends MasterDatabase {

	
	public DiscussionForumCommentsDatabase(String jndiname) throws NamingException, SQLException
	{
			super(jndiname);
	}
	
	public static Boolean saveDiscussionForumCommentsdetails(DiscussionForumComments discussionForumComments) throws SQLException, Exception
	{
		
		Connection connection = null;
		PreparedStatement statement = null;
		Boolean IsInsertSuccessfull = false;
		
		try {
			int count = 0;
			String sqlQuery = "Insert into DISCUSSION_Forum_Comments_SAS (DISCUSSION_ID, COMMENTS, POSTED_BY) "
					         +" values (?, ?, ?)"; 
			DiscussionForumCommentsDatabase discussionForumCommentsDatabase = new DiscussionForumCommentsDatabase("dbuser");
			connection = discussionForumCommentsDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setLong(1, discussionForumComments.getDiscussionId());
			statement.setString(2, discussionForumComments.getComments());
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

	
	public static Queue<DiscussionForumComments> getDiscussionForumCommentsdetails(DiscussionForumComments searchInput) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<DiscussionForumComments> queue = new LinkedList <DiscussionForumComments> ();	
		
		try {		
			String sqlQuery = "SELECT DISCUSSION_ID, COMMENTS, POSTED_BY, DATE_TIME"
			                 + " FROM DISCUSSION_Forum_Comments_SAS WHERE DISCUSSION_ID = ? ORDER BY DATE_TIME Desc";
			
			DiscussionForumCommentsDatabase discussionForumCommentsDatabase = new DiscussionForumCommentsDatabase("dbuser");
			connection = discussionForumCommentsDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			

			statement.setLong(1, searchInput.getDiscussionId());
			
			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				DiscussionForumComments discussionForumComments = new DiscussionForumComments();
				discussionForumComments.setDiscussionId(resultSet.getLong("Discussion_ID"));
				discussionForumComments.setPostedBy(resultSet.getString("Posted_By"));
				discussionForumComments.setComments(resultSet.getString("Comments"));
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

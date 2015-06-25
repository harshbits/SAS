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

import sas.data.storage.objects.Video;

/**
 * @author VASUDEV
 *
 * Contains functions that are used for all SQL operation on Video sas.database. 
 */


public class VideosDatabase extends MasterDatabase {

	private static String dbUser = "dbuser"; 
	
	public VideosDatabase(String jndiname) throws NamingException, SQLException
	{
			super(jndiname);
	}
	
	public static Boolean saveVideodetails(Video video) throws SQLException, Exception
	{
		
		Connection connection = null;
		PreparedStatement statement = null;
		Boolean IsInsertSuccessfull = false;

		try {
			int count = 0;
			String sqlQuery = "Insert into VIDEOS_SAS (TITLE, CATEGORY, COURSE_ID, VIDEO, USERID, FILE_NAME, MIMETYPE) values (?, ?, ?, ?, ?, ?, ?)"; 
			VideosDatabase videosDatabase = new VideosDatabase(VideosDatabase.dbUser);
			connection = videosDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setString(1, video.getTitle());
			statement.setString(2, video.getCategory());
			statement.setString(3, video.getCourseId());
			statement.setBlob(4, video.getInputStream());
			statement.setString(5, video.getUserid());
			statement.setString(6, video.getFileName());
			statement.setString(7, video.getMimetype());
			try {
				count = statement.executeUpdate();
			}
			catch(SQLException e) {
				throw new Exception(e.getSQLState() + " " + e.getMessage());
			}
			
			if (count > 0)
				IsInsertSuccessfull = true;
			else
				IsInsertSuccessfull = false;
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
	
	public static Queue<Video> getVideodetailsForUser(String userid) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<Video> queue = new LinkedList <Video> ();	
		
		try {		
			String sqlQuery = "SELECT VIDEO_ID, TITLE, CATEGORY, USERID, DATE_TIME, FILE_NAME, COURSE_ID"
			                 + " FROM VIDEOS_SAS WHERE UserId = ? ORDER BY DATE_TIME Desc";
			
			VideosDatabase videosDatabase = new VideosDatabase(VideosDatabase.dbUser);
			connection = videosDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			

			if (userid == null)
				userid = "";
			
			statement.setString(1, userid);
			
			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				Video video = new Video();
				
				video.setVideoId(resultSet.getLong("Video_ID"));
				video.setUserid(resultSet.getString("UserId"));
				video.setTitle(resultSet.getString("title"));
				video.setCreatedDate(resultSet.getTimestamp("date_time"));
				video.setCategory(resultSet.getString("Category"));
				video.setCourseId(resultSet.getString("Course_id"));
				video.setFileName(resultSet.getString("file_name"));
				
				queue.add(video);
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
	
	
	
	public static Video getVideoData(long VideoId) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;	
		Video video = new Video();
		try {		
			String sqlQuery = "SELECT VIDEO, FILE_NAME, MIMETYPE FROM VIDEOS_SAS WHERE VIDEO_ID = ?";
			
			VideosDatabase videosDatabase = new VideosDatabase(VideosDatabase.dbUser);
			connection = videosDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			statement.setLong(1, VideoId);
			
			resultSet = statement.executeQuery();		
			
			if (resultSet.next())
			{
				video.setFileName(resultSet.getString("FILE_NAME"));
				Blob blob = resultSet.getBlob("VIDEO");
				video.setMimetype(resultSet.getString("MIMETYPE"));
				video.setLength(blob.length());
				video.setInputStream(blob.getBinaryStream());
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
		return video;
	}
	
	public static Queue<Video> searchVideos(String search) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<Video> queue = new LinkedList <Video> ();
		
		try {
			
			String sqlQuery = "SELECT VIDEO_ID, TITLE, CATEGORY, COURSE_ID, USERID, DATE_TIME, FILE_NAME"
	                 + " FROM VIDEOS_SAS WHERE LOWER(Title) LIKE ? or LOWER(category) LIKE ? or LOWER(COURSE_ID) like ? or LOWER(FILE_NAME) like ?"
					+ " or LOWER(USERID) like ?"
					+ " ORDER BY DATE_TIME Desc";
			
			VideosDatabase videosDatabase = new VideosDatabase(VideosDatabase.dbUser);
			connection = videosDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			

			
			if (search == null)
				search = "";

			search = search.toLowerCase();
			statement.setString(1, "%" + search + "%");
			statement.setString(2, "%" + search + "%");
			statement.setString(3, "%" + search + "%");
			statement.setString(4, "%" + search + "%");
			statement.setString(5, "%" + search + "%");
			
			resultSet = statement.executeQuery();		
			
			while (resultSet.next())
			{
				Video video = new Video();
				
				video.setVideoId(resultSet.getLong("Video_ID"));
				video.setUserid(resultSet.getString("UserId"));
				video.setTitle(resultSet.getString("title"));
				video.setCreatedDate(resultSet.getTimestamp("date_time"));
				video.setCategory(resultSet.getString("Category"));
				video.setCourseId(resultSet.getString("Course_id"));
				video.setFileName(resultSet.getString("file_name"));
				
				queue.add(video);
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

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

import sas.data.storage.objects.Job;

/**
 * @author VASUDEV
 *
 * Contains functions that are used for all SQL operation on Book sas.database. 
 */


public class JobsDatabase extends MasterDatabase {

	
	public JobsDatabase(String jndiname) throws NamingException, SQLException
	{
			super(jndiname);
	}
	
	public static Boolean saveJobdetails(Job job) throws SQLException, Exception
	{
		
		Connection connection = null;
		PreparedStatement statement = null;
		Boolean IsInsertSuccessfull = false;
		
		try {
			int count = 0;
			String sqlQuery = "Insert into JOB_SAS (Title, Skill_set, Job_Function, Experience, Employment_type,Posted_by) "
					         +" values (?, ?, ?, ?, ?,?)"; 
			JobsDatabase jobDatabase = new JobsDatabase("dbuser");
			connection = jobDatabase.getConnection();
			statement = connection.prepareStatement(sqlQuery);			
			
			//statement.setString(1, job.getJob_id() );			
			statement.setString(1,job.getTitle() );
			statement.setString(2, job.getSkill_set());			
			statement.setString(3, job.getJob_function());
			statement.setString(4, job.getExperience());
			statement.setString(5, job.getEmployement_type());
			statement.setString(6, job.getPostedBy());
			
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
	
	public static Queue<Job> searchJobs(String search) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Queue<Job> queue = new LinkedList <Job> ();
		
		try {
			
			String sqlQuery = "SELECT *"
	                 + " FROM JOB_SAS WHERE TITLE LIKE ? OR SKILL_SET LIKE ? OR JOB_FUNCTION LIKE ?"
					+ " ORDER BY DATE_TIME Desc";
			
			JobsDatabase booksDatabase = new JobsDatabase("dbuser");
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
				Job job = new Job();
				
				job.setJob_id(resultSet.getString("JOB_ID"));				
				job.setTitle(resultSet.getString("TITLE"));							
				job.setEmployement_type(resultSet.getString("EMPLOYMENT_TYPE"));
				job.setExperience(resultSet.getString("EXPERIENCE"));
				job.setSkill_set(resultSet.getString("SKILL_SET"));
				job.setJob_function(resultSet.getString("JOB_FUNCTION"));
				job.setPostedBy(resultSet.getString("POSTED_BY"));	
				queue.add(job);
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

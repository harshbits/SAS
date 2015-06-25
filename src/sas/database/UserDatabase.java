/**
 * 
 */
package sas.database;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import sas.data.storage.objects.Cipher;
import sas.data.storage.objects.User;

/**
 * @author VASUDEV
 *
 * Contains functions that are used for all SQL operation on User sas.database. 
 */

public class UserDatabase extends MasterDatabase {

	
	public UserDatabase(String jndiname) throws NamingException, SQLException
	{
			super(jndiname);
	}
	
	public static Boolean CheckforExistingUserId(User user) throws Exception
	{	
		try {
			Boolean result = false;
			String sqlQuery = "Select 1 from User_SAS where UserId = ?";
			
			UserDatabase userDatabase = new UserDatabase("dbuser"); 

			Connection connection = userDatabase.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlQuery);			
			
			statement.setString(1, user.getUserid());
			
			ResultSet resultSet = statement.executeQuery();		
			
			if (resultSet.next() == true)
				result = true;
			
			resultSet.close();
			statement.close();
			connection.close();
			
			return result;
			
		} catch (SQLException e) {
			 throw e;
		}
	}
	
	public static Boolean SaveUserDetails(User user) throws Exception
	{
		Boolean result = false;
		
		try {
			String sqlQuery = "Insert into User_SAS (userid, password, name, category, dept_id) values (?, ?, ?, ?, ?)";
			
			UserDatabase userDatabase = new UserDatabase("dbuser"); 
			
			Connection connection = userDatabase.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlQuery);			
			
			statement.setString(1, user.getUserid());
			statement.setString(2, Cipher.Encrypt(user.getPassword()));
			statement.setString(3, user.getUsername());
			statement.setInt(4, user.getCategory());
			statement.setString(5, user.getDepartment());
			
			int count = statement.executeUpdate();
			
			if (count == 1)
				result = true;
			
			statement.close();
			connection.close();
			
		} catch (SQLException|NamingException|NoSuchAlgorithmException e) {
			throw e;
		}
		
		return result;
	}
	
	public static Boolean UpdateUserDetails(User user) throws Exception
	{
		Boolean result = false;
		
		try {
			String sqlQuery = "UPDATE User_SAS SET password = ?, name = ?, category = ?, dept_id = ? WHERE userid = ?";
			
			UserDatabase userDatabase = new UserDatabase("dbuser"); 
			
			Connection connection = userDatabase.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlQuery);			
			
			statement.setString(1, Cipher.Encrypt(user.getPassword()));
			statement.setString(2, user.getUsername());
			statement.setInt(3, user.getCategory());
			statement.setString(4, user.getDepartment());
			statement.setString(5, user.getUserid());
			
			int count = statement.executeUpdate();
			
			if (count == 1)
				result = true;
			
			statement.close();
			connection.close();
			
		} catch (SQLException|NamingException|NoSuchAlgorithmException e) {
			throw e;
		}
		
		return result;
	}
	
	public static User GetUserDetails(User user) throws Exception 
	{
		
		try {
			String sqlQuery = "SELECT UserId, Name, Password, Category, Dept_id from USER_SAS where UserId = ? and Password = ?";
			
			UserDatabase userDatabase = new UserDatabase("dbuser"); 
			Connection connection = userDatabase.getConnection();
			PreparedStatement statement = connection.prepareStatement(sqlQuery);			

			statement.setString(1, user.getUserid());
			statement.setString(2, Cipher.Encrypt(user.getPassword()));
			
			ResultSet resultSet = statement.executeQuery();		
			
			if (resultSet.next() == true)
			{
				user.setUsername(resultSet.getString("name"));
				user.setCategory(resultSet.getInt("category"));
				user.setDepartment(resultSet.getString("dept_id"));
				
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
			return user;
			
		} catch (Exception e) {
			 throw e;
		}
	}
	
	public static Boolean checkIfUserHasLoggedIn(User user)
	{
		if (user == null || user.getUserid() == null || user.getUserid() == "")
			return false;
		else
			return true;
	}
}

/**
 * 
 */
package sas.database;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author VASUDEV
 *
 * base class. contains connectivity parameters. any new sas.database class should inherit this class. 
 */

public class MasterDatabase {
	
private DataSource dataSource;

	public MasterDatabase(String jndiname) throws NamingException
	{
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			dataSource = (DataSource)envContext.lookup("jdbc/" + jndiname);
	    } catch (NamingException e) {
	        // Handle error that it's not configured in JNDI.
	        throw e;
		}
	}
	
	public Connection getConnection() throws Exception
	{
		try {
			return dataSource.getConnection();
		} catch (Exception e) {
			throw e;
		}
	}
}

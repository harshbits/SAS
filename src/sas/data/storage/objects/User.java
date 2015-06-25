/**
 * 
 */
package sas.data.storage.objects;

/**
 * @author VASUDEV
 *
 * Contains fields that are used for storing the data retrieved from the sas.database or to be inserted into the User sas.database. 
 */
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3933019385357206039L;
	/**
	 * 
	 */
	private String userid;
	private String password;
	private String username;
	private String Department;
	private int Category;
	
	public String getUserid()
	{
		return this.userid;
	}
	
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setUsername(String name)
	{
		this.username = name;
	}
	
	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public int getCategory() {
		return Category;
	}

	public void setCategory(int category) {
		Category = category;
	}

	public User()
	{
		this.userid = null;
		this.password = null;
		this.username = null;
	}
}

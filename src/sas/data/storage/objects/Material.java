/**
 * 
 */
package sas.data.storage.objects;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletResponse;

/**
 * @author VASUDEV
 *
 */
public class Material {

	private static final int BUFFER_SIZE = 4096;   
	
	private String userid;
	private Timestamp createdDate;
	private String category;	
	private String title;
	private String fileName;
	private InputStream InputStream;
	private Long length;
	private String mimetype;
	/**
	 * @return the userid
	 */
	public String getUserid() {
		
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		
		this.userid = userid;
	}
	/**
	 * @return the createdDate
	 */
	public Timestamp getCreatedDate() {
		
		return createdDate;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Timestamp createdDate) {
		
		this.createdDate = createdDate;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		
		this.category = category;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		
		this.title = title;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		
		this.fileName = fileName;
	}
	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		
		return InputStream;
	}
	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		
		InputStream = inputStream;
	}
	
	/**
	 * @param inputStream the inputStream to close
	 */
	
	public void closeInputStream() throws IOException
	{
		this.InputStream.close();
	}
	
	
	/**
	 * @return the length
	 */
	public Long getLength() {
		
		return length;
	}
	/**
	 * @param length the length to set
	 */
	public void setLength(Long length) {
		
		this.length = length;
	}
	/**
	 * @return the mimetype
	 */
	public String getMimetype() {

		return mimetype;
	}
	/**
	 * @param mimetype the mimetype to set
	 */
	public void setMimetype(String mimetype) {
		
		this.mimetype = mimetype;
	}	
	
	/**
	 * set the response content properties and header attributes of the response 
	 * 
	 * @param response
	 */
	
	public void setResponseProperties(HttpServletResponse response) {
		
		String headerKey = "Content-Disposition";
	    String headerValue = String.format("attachment; filename=\"%s\"", this.getFileName());		
        response.setContentType(this.getMimetype());
        response.setContentLengthLong(this.getLength());
        response.setHeader(headerKey, headerValue);
	}
	
	/**
	 * writes the file to the client
	 * 
	 * @param outputStream
	 * @throws IOException 
	 */
	
	public void writeToStream(OutputStream outputStream) throws IOException {

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
         
        while ((bytesRead = this.getInputStream().read(buffer)) != -1) {
        	
            outputStream.write(buffer, 0, bytesRead);
        }
         
        this.getInputStream().close();
        outputStream.close();             
	}
}

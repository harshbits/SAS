/**
 * 
 */
package sas.data.storage.objects;

/**
 * @author VASUDEV
 *
 * Contains fields that are used for storing the data retrieved from the sas.database or to be inserted into the Book sas.database. 
 */
public class Book extends Material {

	/**
	 * 
	 */
	private Long bookId;
	private String author;
	private String edition;

	/**
	 * 
	 */

	public Long getBookId() {
		
		return bookId;
	}

	public void setBookId(Long bookId) {
		
		this.bookId = bookId;
	}

	
	public String getAuthor() {
		
		return author;
	}
	
	public void setAuthor(String author) {
		
		this.author = author;
	}
	
	public String getEdition() {
		
		return edition;
	}
	
	public void setEdition(String edition) {
		
		this.edition = edition;
	}
}


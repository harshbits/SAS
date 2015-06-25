/**
 * 
 */
package sas.data.storage.objects;
import java.sql.Timestamp;

/**
 * @author VASUDEV
 *
 * Contains fields that are used for storing the data retrieved from the sas.database or to be inserted into the Book sas.database. 
 */
public class DiscussionForum {

	/**
	 * 
	 */
	private Long discussionId;
	private String title;
	private String description;
	private String postedBy;
	private Timestamp dateTime;
	/**
	 * 
	 */
	/**
	 * @return the discussionId
	 */
	public Long getDiscussionId() {
		return discussionId;
	}
	/**
	 * @param discussionId the discussionId to set
	 */
	public void setDiscussionId(Long discussionId) {
		this.discussionId = discussionId;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the postedBy
	 */
	public String getPostedBy() {
		return postedBy;
	}
	/**
	 * @param postedBy the postedBy to set
	 */
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	/**
	 * @return the dateTime
	 */
	public Timestamp getDateTime() {
		return dateTime;
	}
	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	
	
	
}


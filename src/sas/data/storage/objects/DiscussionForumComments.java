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
public class DiscussionForumComments {

	/**
	 * 
	 */
	private Long discussionId;
	private String comments;
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
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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


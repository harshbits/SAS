/**
 * 
 */
package sas.data.storage.objects;
import java.sql.Timestamp;

/**
 * @author VASUDEV
 *
 * Contains fields that are used for storing the data retrieved from the sas.database or to be inserted into the QA_FORUM_SAS table. 
 */
public class QAForumReplies {

	/**
	 * 
	 */
	private Long questionId;
	private String reply;
	private String postedBy;
	private Timestamp dateTime;
	/**
	 * 
	 */
	/**
	 * @return the questionId
	 */
	public Long getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return the reply
	 */
	public String getReply() {
		return (reply ==  null) ? "" : reply;
	}
	/**
	 * @param reply the reply to set
	 */
	public void setReply(String reply) {
		this.reply = reply;
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


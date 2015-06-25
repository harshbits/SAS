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
public class QAForum {

	/**
	 * 
	 */
	private Long questionId;
	private String question;
	private String title;
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
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
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


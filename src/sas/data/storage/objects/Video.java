/**
 * 
 */
package sas.data.storage.objects;

/**
 * @author VASUDEV
 *
 * Contains fields that are used for storing the data retrieved from the sas.database or to be inserted into the Book sas.database. 
 */
public class Video extends Material {

	/**
	 * 
	 */
	private Long videoId;
	private String courseId;

	/**
	 * 
	 */

	public Long getVideoId() {
		
		return videoId;
	}

	public void setVideoId(Long videoId) {
		
		this.videoId = videoId;
	}

	public String getCourseId() {
		
		return courseId;
	}

	public void setCourseId(String courseId) {
		
		this.courseId = courseId;
	}
}


package sas.data.storage.objects;

import java.sql.Timestamp;

public class Job 
{
	private String jobId;
	private String Title;
	private String experience;
	private String job_function;
	private String employement_type;
	private String skillSet;
	private String postedBy;
	private Timestamp dateTime;
	
	public String getJob_id() {
		return jobId;
	}
	public void setJob_id(String job_id) {
		this.jobId = job_id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getJob_function() {
		return job_function;
	}
	public void setJob_function(String job_function) {
		this.job_function = job_function;
	}
	public String getEmployement_type() {
		return employement_type;
	}
	public void setEmployement_type(String employement_type) {
		this.employement_type = employement_type;
	}
	public String getSkill_set() {
		return skillSet;
	}
	public void setSkill_set(String skill_set) {
		this.skillSet = skill_set;
	}
	public String getPostedBy() {
		return postedBy;
	}
	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}
	public Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	
	

}

package sas.data.storage.objects;

import java.sql.Timestamp;

public class News {
	
	private Long newsId;
	private String newsTitle;
	private String newsLink;
	private String postedBy;
	private Timestamp dateTime;
	private String category;
	private String NewsContent;
	
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsLink() {
		return newsLink;
	}
	public void setNewsLink(String newsLink) {
		this.newsLink = newsLink;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getNewsContent() {
		return NewsContent;
	}
	public void setNewsContent(String newsContent) {
		NewsContent = newsContent;
	}
	
	
	
}

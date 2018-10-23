package com.twd.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
@Table(name ="feedback")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long feedbackId;
	
	@NotEmpty(message = "Name cannot be empty")
	@Size(min = 3, max = 20, message = "Name length must be between 3 to 20 chars")
	private String userName;
	
	@NotEmpty(message = "Email ID cannot be null")
	@Email(message = "Invalid Email Id")
	private String emailId;
	
	@NotEmpty(message = "Mobile number cannot be empty")
	@Pattern(regexp = "\\d{10}", message = "mobile number can be only 10 digits")
	private String mobileNumber;
	
	@Enumerated(EnumType.STRING)
	private QueryType queryType;
	
	private String concern;
	
	public long getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public String getConcern() {
		return concern;
	}

	public void setConcern(String concern) {
		this.concern = concern;
	}
	
	
	
	
}

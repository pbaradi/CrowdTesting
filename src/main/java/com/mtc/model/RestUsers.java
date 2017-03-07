package com.mtc.model;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestUsers {
	private String firstName;
	private String lastName;
	private String password;
	private String emailId;
	private int userId;
	private String userFlag;
	private int amount;
	//private List<RestProjects> projects;
	
	
	public RestUsers() {
		super();
	}
	
	
	public RestUsers(String firstName, String lastName, String password, String emailId, int userId, String userFlag,
			int amount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailId = emailId;
		this.userId = userId;
		this.userFlag = userFlag;
		this.amount = amount;
		//this.projects = projects;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserFlag() {
		return userFlag;
	}
	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
//	public List<ProjectVO> getProjects() {
//		return projects;
//	}
//	public void setProjects(List<ProjectVO> projects) {
//		this.projects = projects;
//	}
	

}

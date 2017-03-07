/**
 * 
 */
package com.mtc.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String password;
	private String emailId;
	private int userId;
	private String userFlag;
	private int amount;

	private List<ProjectVO> projects;
	private List<BugVO> bugs;


	/**
	 * 
	 */
	public UserVO() {
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param emailId
	 * @param userId
	 * @param userFlag
	 * @param amount
	 */
	public UserVO(String firstName, String lastName, String password, String emailId, int userId, String userFlag,
			int amount) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailId = emailId;
		this.userId = userId;
		this.userFlag = userFlag;
		this.amount = amount;
	}


	/**
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param emailId
	 * @param userId
	 * @param userFlag
	 * @param amount
	 * @param projectlist
	 */
	public UserVO(String firstName, String lastName, String password, String emailId, int userId, String userFlag,
			int amount, List<ProjectVO> projectlist) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.emailId = emailId;
		this.userId = userId;
		this.userFlag = userFlag;
		this.amount = amount;
		this.projects = projectlist;
	}

	public UserVO(String password, String emailId,String userFlag) {
		// TODO Auto-generated constructor stub
		this.password = password;
		this.emailId = emailId;
		this.userFlag = userFlag;
	}

	/**
	 * @return the firstName
	 */
	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * @return the password
	 */
	@Column(name = "password", unique = true, nullable = false)
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the emailId
	 */
	@Column(name = "email_id", unique = true, nullable = false)
	public String getEmailId() {
		return emailId;
	}


	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	/**
	 * @return the userId
	 */
	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}


	/**
	 * @return the userFlag
	 */
	@Column(name = "user_flag")
	public String getUserFlag() {
		return userFlag;
	}


	/**
	 * @param userFlag the userFlag to set
	 */
	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}


	/**
	 * @return the amount
	 */
	@Column(name = "amount")
	public int getAmount() {
		return amount;
	}


	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the projects
	 */
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="users")
		//	,fetch=FetchType.EAGER)
	public List<ProjectVO> getProjects() {
		return projects;
	}

	/**
	 * @param projectlist the projects to set
	 */
	public void setProjects(List<ProjectVO> projects) {
		this.projects = projects;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch=FetchType.EAGER, mappedBy = "user")
	public List<BugVO> getBugs() {
		return bugs;
	}


	public void setBugs(List<BugVO> bugs) {
		this.bugs = bugs;
	}


	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("FirstName :"+getFirstName());
		sb.append(" lastName :"+getLastName());
		sb.append(" emailId :"+getEmailId());
		sb.append(" userFlag :"+getUserFlag());
		sb.append(" amount :"+getAmount());
		sb.append(" userid :"+getUserId());
		return sb.toString();
	}

}

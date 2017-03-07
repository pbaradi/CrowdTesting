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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class ProjectVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int projectId;
	private String projectName;
	private String projectDesc;
	private String testType;
	private String projectCategory;
	private String deadLine;
	private List<BugVO> bugs;
	private List<UserVO> users;

	/**
	 * 
	 */
	public ProjectVO() {
		super();
	}

	/**
	 * @param projectId
	 * @param projectName
	 * @param projectDesc
	 * @param testType
	 * @param projectCategory
	 * @param deadLine
	 */
	public ProjectVO(int projectId, String projectName, String projectDesc, String testType, String projectCategory,
			String deadLine) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.testType = testType;
		this.projectCategory = projectCategory;
		this.deadLine = deadLine;
	}
	
	public ProjectVO(int projectId){
		this.projectId = projectId;
	}

	/**
	 * @param projectId
	 * @param projectName
	 * @param projectDesc
	 * @param testType
	 * @param projectCategory
	 * @param deadLine
	 * @param bugList
	 */
	public ProjectVO(int projectId, String projectName, String projectDesc, String testType, String projectCategory,
			String deadLine, List<BugVO> bugList) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.testType = testType;
		this.projectCategory = projectCategory;
		this.deadLine = deadLine;
		this.bugs = bugList;
	}
	
	public ProjectVO(String projectName, String projectDesc, String testType, String projectCategory,
			String deadLine) {
		super();
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.testType = testType;
		this.projectCategory = projectCategory;
		this.deadLine = deadLine;
	}

	/**
	 * @return the projectId
	 */
	@Id
	 @Column(name = "project_id", unique = true, nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 public int getProjectId() {
		 return projectId;
	 }

	 /**
	  * @param projectId the projectId to set
	  */
	 public void setProjectId(int projectId) {
		 this.projectId = projectId;
	 }

	 /**
	  * @return the projectName
	  */
	 @Column(name = "project_name")
	 public String getProjectName() {
		 return projectName;
	 }

	 /**
	  * @param projectName the projectName to set
	  */
	 public void setProjectName(String projectName) {
		 this.projectName = projectName;
	 }

	 /**
	  * @return the projectDesc
	  */
	 @Column(name = "project_desc")
	 public String getProjectDesc() {
		 return projectDesc;
	 }

	 /**
	  * @param projectDesc the projectDesc to set
	  */
	 public void setProjectDesc(String projectDesc) {
		 this.projectDesc = projectDesc;
	 }

	 /**
	  * @return the testType
	  */
	 @Column(name = "test_type")
	 public String getTestType() {
		 return testType;
	 }

	 /**
	  * @param testType the testType to set
	  */
	 public void setTestType(String testType) {
		 this.testType = testType;
	 }

	 /**
	  * @return the projectCategory
	  */
	 @Column(name = "project_category")
	 public String getProjectCategory() {
		 return projectCategory;
	 }

	 /**
	  * @param projectCategory the projectCategory to set
	  */
	 public void setProjectCategory(String projectCategory) {
		 this.projectCategory = projectCategory;
	 }

	 /**
	  * @return the deadLine
	  */
	 @Column(name = "last_date")
	 public String getDeadLine() {
		 return deadLine;
	 }

	 /**
	  * @param deadLine the deadLine to set
	  */
	 public void setDeadLine(String deadLine) {
		 this.deadLine = deadLine;
	 }


	 /**
	  * @return the bugList
	  */
	 @OneToMany(cascade = { CascadeType.ALL }, fetch=FetchType.EAGER, mappedBy = "project")
	 public List<BugVO> getBugs() {
		 return bugs;
	 }

	 /**
	  * @param bugList the bugList to set
	  */
	 public void setBugs(List<BugVO> bugList) {
		 this.bugs = bugList;
	 }

	 /**
	  * @return the userList
	  */
	 @ManyToMany(cascade = { CascadeType.ALL })
	 @JoinTable(name = "USER_PROJECTS", 
	 joinColumns = { @JoinColumn(name = "project_id") }, 
	 inverseJoinColumns = { @JoinColumn(name = "user_id") })
	 public List<UserVO> getUsers() {
		 return users;
	 }

	 /**
	  * @param userList the userList to set
	  */
	 public void setUsers(List<UserVO> userList) {
		 this.users = userList;
	 }

	 public String toString(){
		 StringBuffer sb = new StringBuffer();
		 sb.append("ProjectId : "+getProjectId());
		 sb.append(" ProjectName : "+getProjectName());
		 sb.append(" ProjectDesc : "+getProjectDesc());
		 sb.append(" testType : "+getTestType());
		 sb.append(" category : "+getProjectCategory());
		 sb.append(" deadline : "+getDeadLine());
		 return sb.toString();
	 }

}

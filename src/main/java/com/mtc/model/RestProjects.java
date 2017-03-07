/**
 * 
 */
package com.mtc.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class RestProjects {

	
	private int projectId;
	private String projectName;
	private String projectDesc;
	private String testType;
	private String projectCategory;
	private String deadLine;
	private List<BugVO> bugs;
	private List<UserVO> users;

	
	public RestProjects() {
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
	public RestProjects(int projectId, String projectName, String projectDesc, String testType, String projectCategory,
			String deadLine) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.testType = testType;
		this.projectCategory = projectCategory;
		this.deadLine = deadLine;
	}

	
	
	 public int getProjectId() {
		 return projectId;
	 }

	
	 public void setProjectId(int projectId) {
		 this.projectId = projectId;
	 }

	
	 public String getProjectName() {
		 return projectName;
	 }

	 public void setProjectName(String projectName) {
		 this.projectName = projectName;
	 }

	
	 public String getProjectDesc() {
		 return projectDesc;
	 }

	 public void setProjectDesc(String projectDesc) {
		 this.projectDesc = projectDesc;
	 }

	
	 public String getTestType() {
		 return testType;
	 }

	 
	 public void setTestType(String testType) {
		 this.testType = testType;
	 }

	
	 public String getProjectCategory() {
		 return projectCategory;
	 }

	
	 public void setProjectCategory(String projectCategory) {
		 this.projectCategory = projectCategory;
	 }


	 public String getDeadLine() {
		 return deadLine;
	 }

	
	 public void setDeadLine(String deadLine) {
		 this.deadLine = deadLine;
	 }



	 public List<BugVO> getBugs() {
		 return bugs;
	 }

	 
	 public void setBugs(List<BugVO> bugList) {
		 this.bugs = bugList;
	 }


	 public List<UserVO> getUsers() {
		 return users;
	 }

	
	 public void setUsers(List<UserVO> userList) {
		 this.users = userList;
	 }


}

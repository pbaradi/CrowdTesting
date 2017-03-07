package com.mtc.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RestBugs{
	

	private int bugId;
	private String bugDescription;
	private String bugType;
	private ProjectVO project;
	private UserVO user;
	


	public RestBugs() {
		super();
	}

	/**
	 * @param bugId
	 * @param bugDescription
	 */
	public RestBugs(int bugId, String bugDescription) {
		super();
		this.bugId = bugId;
		this.bugDescription = bugDescription;
	}
	
	public RestBugs(int bugId, String bugDescription, String bugType) {
		super();
		this.bugId = bugId;
		this.bugDescription = bugDescription;
		this.bugType = bugType;
	}

	public RestBugs( UserVO user,ProjectVO project, String bugDescription, String bugType ,int bugId) {
		super();
		this.project = project;
		this.user = user;
		this.bugId = bugId;
		this.bugDescription = bugDescription;
		this.bugType = bugType;
		
	}

	public int getBugId() {
		return bugId;
	}

	
	public void setBugId(int bugId) {
		this.bugId = bugId;
	}


	public String getBugDescription() {
		return bugDescription;
	}

	
	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}
	

	public String getBugType() {
		return bugType;
	}

	public void setBugType(String bugType) {
		this.bugType = bugType;
	}

	public ProjectVO getProject() {
		return project;
	}

	public void setProject(ProjectVO project) {
		this.project = project;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}



}

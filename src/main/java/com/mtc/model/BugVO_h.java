package com.mtc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bug")
public class BugVO_h implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bugId;
	private String bugDescription;
	private String bugType;
	private ProjectVO project;
	private UserVO user;

	public BugVO_h() {
		super();
	}

	/**
	 * @param bugId
	 * @param bugDescription
	 */
	public BugVO_h(int bugId, String bugDescription) {
		super();
		this.bugId = bugId;
		this.bugDescription = bugDescription;
	}

	public BugVO_h( UserVO user,ProjectVO project, String bugDescription, String bugType ,int bugId) {
		super();
		this.project = project;
		this.user = user;
		this.bugId = bugId;
		this.bugDescription = bugDescription;
		this.bugType = bugType;
		
	}

	
	/**
	 * @param bugId
	 * @param bugDescription
	 */
	public BugVO_h(int bugId, String bugDescription, String  bugType) {
		super();
		this.bugId = bugId;
		this.bugDescription = bugDescription;
		this.bugType= bugType;
	}

	
	
	public BugVO_h(UserVO user, ProjectVO project, int bugId, String bugDescription, String bugType) {
		this.bugId = bugId;
		this.bugDescription = bugDescription;
		this.bugType= bugType;
		this.project = project;
		this.user = user;
	}

	/**
	 * @return the bugId
	 */
	@Id
    @Column(name = "bug_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getBugId() {
		return bugId;
	}

	/**
	 * @param bugId the bugId to set
	 */
	public void setBugId(int bugId) {
		this.bugId = bugId;
	}

	/**
	 * @return the bugDescription
	 */
	@Column(name = "bug_desc")
	public String getBugDescription() {
		return bugDescription;
	}

	/**
	 * @param bugDescription the bugDescription to set
	 */
	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}
	
	@Column(name = "bug_type")
	public String getBugType() {
		return bugType;
	}

	public void setBugType(String bugType) {
		this.bugType = bugType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id", nullable = false)
	public ProjectVO getProject() {
		return project;
	}

	public void setProject(ProjectVO project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(" Bugid : "+getBugId());
		sb.append(" desc : "+getBugDescription());
		return sb.toString();
	}


}

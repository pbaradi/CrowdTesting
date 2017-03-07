package com.mtc.dao;

import java.util.List;

import com.mtc.model.ProjectVO;

public interface ProjectDAO {
	
	public ProjectVO addProject(ProjectVO projectVO);
	public List<ProjectVO> getProjects();
	public List<ProjectVO> getProjectUsers();
	public ProjectVO getProject(int projectId);
	public ProjectVO getProjectDetails(ProjectVO projectVO);
	public ProjectVO updateProject(ProjectVO projectVO);
	public List<ProjectVO> getProjectUsers(int userId);

}

package com.mtc.dao;

import java.util.List;

import com.mtc.model.BugVO;
import com.mtc.model.ProjectVO;
import com.mtc.model.UserVO;

public interface UserDAO {
	
	public UserVO registerUser(UserVO userVO);
	
	public UserVO login(String emailId, String password, String userFlag);
	
	public List<UserVO> getUsers();
	
	public List<UserVO> getTesterUsers(); 
	
	public List<UserVO> getClients();
	
	public UserVO getUser(int userId);
	
	public List<ProjectVO> getUserProjects(int userId, ProjectVO projectVO);
	
	public UserVO updateProjectUser(int projectId, int userId);
	
	public UserVO submitBug(int userId, int projectId, List<BugVO> bugVO);
	
	public UserVO updateUser(UserVO userVO);

}

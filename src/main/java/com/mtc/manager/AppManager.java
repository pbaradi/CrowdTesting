package com.mtc.manager;

import java.io.File;
import java.util.List;

import com.mtc.model.BugVO;
import com.mtc.model.ProjectVO;
import com.mtc.model.UserVO;

public interface AppManager {
	
	public UserVO uploadApp(int userId, ProjectVO projectVO , File file);
	
	public UserVO loginClient(String emailId, String password,String UserFlag);
	public UserVO registerClient(UserVO userVO);
	public List<UserVO> getClients();

	public UserVO loginAdmin(String emailId, String password, String userFlag);
	public List<BugVO> BugByProjectID(int projectId);
	
	public List<BugVO> BugByUserID(int userId);
	 public List<ProjectVO> getProjectUsers(int userId);
	

}

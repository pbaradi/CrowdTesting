package com.mtc.manager;

import java.io.File;
import java.util.List;

import com.mtc.model.BugVO;
import com.mtc.model.ProjectVO;
import com.mtc.model.UserVO;

public interface TesterManager {
	
	public UserVO testerLogin(String emailId, String password,String UserFlag);
	public UserVO registerTester(UserVO userVO);
	public File downloadApp(int projectId, int userId);
	public BugVO submitBug(BugVO bugVO);
	
	public UserVO evaluateIncentive(int userId);
	
	public BugVO updateUserBug(BugVO bugVO);
	public List<UserVO> getTesters();
	public List<ProjectVO> getProjects();

}


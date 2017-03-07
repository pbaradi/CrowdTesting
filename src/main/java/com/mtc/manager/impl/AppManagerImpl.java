package com.mtc.manager.impl;

import java.io.File;
import java.util.List;

import com.mtc.dao.BugDAO;
import com.mtc.dao.ProjectDAO;
import com.mtc.dao.UserDAO;
import com.mtc.dao.impl.BugDAOImpl;
import com.mtc.dao.impl.ProjectDAOImpl;
import com.mtc.dao.impl.UserDAOImpl;
import com.mtc.manager.AppManager;
import com.mtc.model.BugVO;
import com.mtc.model.ProjectVO;
import com.mtc.model.UserVO;
import com.mtc.util.S3Util;

public class AppManagerImpl implements AppManager {

	public UserVO uploadApp(int userId, ProjectVO projectVOfromRest, File file) {
		//int userId = 1;
		System.out.println("in AppManager");
		UserDAO userDAO = new UserDAOImpl();
		ProjectDAO projectDAO = new ProjectDAOImpl();
		ProjectVO newProjectVO  = new ProjectVO(projectVOfromRest.getProjectName(), projectVOfromRest.getProjectDesc(), projectVOfromRest.getTestType(), projectVOfromRest.getProjectCategory(),
				projectVOfromRest.getDeadLine());
		//ProjectVO newProjectVO = new ProjectVO("COstco","Costco Application","Functional","Functional","2015-12-12");
		ProjectVO projectVO = projectDAO.addProject(newProjectVO);
		//projectDAO.addProject(projectVO);
		UserVO userVO = userDAO.updateProjectUser(projectVO.getProjectId(), userId);
	
		S3Util util = new S3Util();
		util.uploadApp(Integer.toString(projectVO.getProjectId()), file);		
		return userVO;

	}

	public UserVO loginClient(String emailId, String password, String userFlag) {
		UserDAO userDAO = new UserDAOImpl();
		UserVO userVO = userDAO.login(emailId, password, userFlag);
		return userVO;
	}
	
	public UserVO loginAdmin(String emailId, String password, String userFlag) {
		UserDAO userDAO = new UserDAOImpl();
		UserVO userVO = userDAO.login(emailId, password, userFlag);
		return userVO;
	}

	public UserVO registerClient(UserVO userVO) {
		UserDAO userDAO = new UserDAOImpl();
		userVO.setUserFlag("client");
		userVO = userDAO.registerUser(userVO);
		return userVO;
	}

	public List<UserVO> getClients() {
		UserDAO userDAO = new UserDAOImpl();
		List<UserVO> userVOs = userDAO.getClients();
		return userVOs;
	}
	
	public List<BugVO> BugByProjectID(int projectId)
	{
		BugDAO bugDAO = new BugDAOImpl();
		List<BugVO> bugVOs = bugDAO.BugByProjectID(projectId);
		return bugVOs;	
	}
	
	public List<BugVO> BugByUserID(int userId)
	{
		BugDAO bugDAO = new BugDAOImpl();
		List<BugVO> bugVOs = bugDAO.BugByUserID(userId);
		return bugVOs;	
	}
	public List<ProjectVO> getProjectUsers(int userId)
    {
    ProjectDAO projectDAO = new ProjectDAOImpl();
    List<ProjectVO> projectVOs = projectDAO.getProjectUsers(userId);
    return projectVOs;    
        
    }

}

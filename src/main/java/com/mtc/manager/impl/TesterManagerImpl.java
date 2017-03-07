package com.mtc.manager.impl;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

import com.mtc.dao.BugDAO;
import com.mtc.dao.ProjectDAO;
import com.mtc.dao.UserDAO;
import com.mtc.dao.impl.BugDAOImpl;
import com.mtc.dao.impl.ProjectDAOImpl;
import com.mtc.dao.impl.UserDAOImpl;
import com.mtc.manager.TesterManager;
import com.mtc.model.BugVO;
import com.mtc.model.ProjectVO;
import com.mtc.model.UserVO;
import com.mtc.util.Constants;
import com.mtc.util.S3Util;
import com.mtc.util.S3Util_old;

public class TesterManagerImpl implements TesterManager {

	public UserVO testerLogin(String emailId, String password,String UserFlag) {
		UserDAO userDAO = new UserDAOImpl();
		UserVO userVO = userDAO.login(emailId, password, UserFlag);
		return userVO;
	}

	public UserVO registerTester(UserVO userVO) {
		UserDAO userDAO = new UserDAOImpl();
		userVO.setUserFlag("tester");
		userVO = userDAO.registerUser(userVO);
		return userVO;
	}

	public File downloadApp(int projectId, int userId) {
		UserDAO userDAO = new UserDAOImpl();
		UserVO userVO = userDAO.updateProjectUser(projectId, userId);
		S3Util util = new S3Util();
		File file = util.downloadApp(Integer.toString(projectId));		
		return file;
	}

	public BugVO submitBug(BugVO bugVO) {
		BugDAO bugDAO = new BugDAOImpl();
		bugVO = bugDAO.addBug(bugVO);
		return bugVO;
	
	}

	public UserVO evaluateIncentive(int userId) {
	UserDAO userDAO = new UserDAOImpl();
		UserVO userVO = userDAO.getUser(userId);
		List<BugVO> bugVOs = userVO.getBugs();
		
		int amount = userVO.getAmount();
		System.out.println("Initial Amount : "+amount);
		
			for (BugVO bugVO : bugVOs) {
				if(bugVO.getBugType().equals(Constants.CRITICAL))
					amount = amount + Constants.BUG_CRITICAL;
				else if(bugVO.getBugType().equals(Constants.HIGH))
					amount = amount + Constants.BUG_HIGH;
				else if(bugVO.getBugType().equals(Constants.LOW))
					amount =amount + Constants.BUG_LOW;
			}
			System.out.println("Updated Amount : "+amount);
		
		userVO.setAmount(amount);
		userDAO.updateUser(userVO);
		return userVO;
	}

	/**
	 * TestManager updates user bugs(CRITICAL,HIGH,LOW) to calculate incentive
	 * Based on this, amount will be finalized
	 */
	public BugVO updateUserBug(BugVO bugVO) {
		BugDAO bugDAO = new BugDAOImpl();
		bugVO = bugDAO.updateBug(bugVO);
		return bugVO;
		
	}

	public List<UserVO> getTesters() {
		UserDAO userDAO = new UserDAOImpl();
		List<UserVO> userVOs = userDAO.getTesterUsers();
		return userVOs;
	}
	
	public List<ProjectVO> getProjects()
	{
		ProjectDAO projectDAO = new ProjectDAOImpl();
		List<ProjectVO> projectVOs = projectDAO.getProjects();
		return projectVOs;
	}


	public static void main(String args[]){
		
		TesterManagerImpl ob = new TesterManagerImpl();
		ProjectDAO projectdao = new ProjectDAOImpl();
		List<ProjectVO> project = projectdao.getProjects();
		for(ProjectVO projectvo : project){
			System.out.println("projectName "+projectvo.getProjectName() +" Desc : "+projectvo.getProjectDesc());

		}
		List<UserVO> testers = ob.getTesters();
		for(UserVO tester : testers){
			System.out.println("tester : " +tester);
		}
		
	}	
}

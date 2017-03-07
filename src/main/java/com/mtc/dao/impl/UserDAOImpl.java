package com.mtc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.mtc.dao.UserDAO;
import com.mtc.model.BugVO;
import com.mtc.model.ProjectVO;
import com.mtc.model.UserVO;
import com.mtc.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	public UserVO registerUser(UserVO userVO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			int userId = (Integer) session.save(userVO);
			userVO.setUserId(userId);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return userVO;
	}

	public UserVO login(String emailId, String password, String userFlag) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		Query query = null;
		UserVO userVO = null;
		try{
			
			tx = session.beginTransaction();
			
			query = session.createQuery("FROM UserVO where email_id = :emailId and password = :password and user_flag = :userFlag");
			query.setString("emailId", emailId);
			query.setString("password", password);
			query.setString("userFlag", userFlag);
			userVO = (UserVO)query.uniqueResult();
			System.out.println(userVO);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return userVO;
	}

	public List<UserVO> getUsers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<UserVO> users = null;
		try{
			tx = session.beginTransaction();
			users = session.createQuery("from UserVO").list();
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return users;
	}

	public List<UserVO> getTesterUsers() 
	 {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<UserVO> users = null;
		try{
			tx = session.beginTransaction();
			users = session.createQuery("from UserVO where user_flag ='tester'").list();
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return users;
	}

	public List<UserVO> getClients() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		List<UserVO> users = null;
		try{
			tx = session.beginTransaction();
			Query q = session.createQuery("from UserVO where userFlag = :userFlag");
			q.setString("userFlag","client");
			users = q.list();
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return users;
	}
	
	
	public UserVO getUser(int userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		UserVO users = null;
		try{
			tx = session.beginTransaction();
			users = (UserVO) session.createCriteria(UserVO.class).add(Restrictions.idEq(userId)).
			setFetchMode("projects", FetchMode.EAGER).setFetchMode("bugs", FetchMode.EAGER).uniqueResult();  
			//users = session.get(UserVO.class, userId);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return users;
	}

	public List<ProjectVO> getUserProjects(int userId, ProjectVO projectVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserVO updateProjectUser(int projectId, int userId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		UserVO user = null;
		ProjectVO projectVO = null;
		try{
			tx = session.beginTransaction();
			user = session.get(UserVO.class, userId);
			projectVO = session.get(ProjectVO.class, projectId);
			List<ProjectVO> projectVOs = new ArrayList<ProjectVO>();
			projectVOs.add(projectVO);
			user.setProjects(projectVOs);
			session.update(user);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return user;
	}

	public UserVO submitBug(int userId, int projectId, List<BugVO> bugVOs){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		UserVO user = null;
		try{
			tx = session.beginTransaction();
			user = session.get(UserVO.class, userId);
			List<ProjectVO> projectVOs = user.getProjects();
			for (ProjectVO projectVO : projectVOs) {
				if(projectVO.getProjectId() == projectId){
					if(projectVO.getBugs()!=null){
						projectVO.getBugs().addAll(bugVOs);
					}else{
						projectVO.setBugs(bugVOs);
					}
					projectVOs.add(projectVO);
					user.setProjects(projectVOs);
					session.update(user);
				}
			}
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return user;
	}

	public UserVO updateUser(UserVO userVO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.update(userVO);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return userVO;
	}
	
	public static void main(String arg[]){
		UserDAO dao = new UserDAOImpl();
		List<UserVO> users = dao.getUsers();
		for (UserVO userVO : users) {
			System.out.println("username "+userVO.getEmailId() +" password : "+userVO.getPassword()+" Projects : "+userVO.getProjects());
		}
	    /*UserVO user = dao.login("shelly@gmail.com", "passworda");
	    System.out.println(user);*/
		/*UserVO user = new UserVO("shelly","tomar","password2","shelly@gmail.com",1,"tester",100);
		user = dao.updateUser(user);
		System.out.println(user);*/
	}	
}

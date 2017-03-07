package com.mtc.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mtc.dao.BugDAO;
import com.mtc.model.BugVO;
import com.mtc.model.ProjectVO;
import com.mtc.model.UserVO;
import com.mtc.util.HibernateUtil;

public class BugDAOImpl implements BugDAO {

	public BugVO addBug(BugVO bugVO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			int bugId = (Integer) session.save(bugVO);
			bugVO.setBugId(bugId);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return bugVO;
	}

	public List<BugVO> getBugs() {
		// TODO Auto-generated method stub
		return null;
	}

	public BugVO getBugDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	public BugVO updateBug(BugVO bugVO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(bugVO);
			session.update(bugVO);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return bugVO;
	}

	public int deleteBug(BugVO bugVO) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<BugVO> BugByProjectID(int projectId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		//BugVO bugVOs = null;
		List<BugVO> bugVOs = null;
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery("from BugVO where project.projectId ="+projectId);
			bugVOs = (List<BugVO>)query.list();
			System.out.println(bugVOs);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return bugVOs;
		
		
	}
	
	public List<BugVO> BugByUserID(int userId){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		//BugVO bugVOs = null;
		List<BugVO> bugVOs = null;
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery("from BugVO where user.userId ="+userId);
			bugVOs = (List<BugVO>)query.list();
			System.out.println(bugVOs);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return bugVOs;
		
		
	}

	
	 public static void main(String args[]){
		      UserVO user = new UserVO();
			  user.setUserId(1);
			
			  ProjectVO project = new ProjectVO();
			  project.setProjectId(1);
			  
		 BugDAO dao = new BugDAOImpl();
		// BugVO bug = new BugVO("user","project",1,"Password bug","Low");
		// dao.addBug(bug);
		// BugVO bug = new BugVO(user,project,1,"Password bug","Low");
		/* bug = dao.updateBug(bug);
		 System.out.println(bug);*/
	 }
		
}

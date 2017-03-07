package com.mtc.dao.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.mtc.dao.ProjectDAO;
import com.mtc.model.ProjectVO;
import com.mtc.model.UserVO;
import com.mtc.util.HibernateUtil;

public class ProjectDAOImpl implements ProjectDAO {

	public ProjectVO addProject(ProjectVO projectVO) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			int projectId = (Integer) session.save(projectVO);
			projectVO.setProjectId(projectId);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projectVO;
	}
	
	public ProjectVO getProject(int projectId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ProjectVO projects = null;
		try{
			tx = session.beginTransaction();
			projects = session.get(ProjectVO.class, projectId);
			tx.commit();
		}catch(HibernateException e){
			if (tx!=null) tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return projects;
	}

	public List<ProjectVO> getProjects() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = null;
			List<ProjectVO> projects = null;
			try{
				tx = session.beginTransaction();
				projects = session.createQuery("from ProjectVO").list();
				tx.commit();
			}catch(HibernateException e){
				if (tx!=null) tx.rollback();
				e.printStackTrace();
			}finally{
				session.close();
			}
			return projects;
		}
	
	public List<ProjectVO> getProjectUsers(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
    UserVO user = null;
        try{
            tx = session.beginTransaction();
            user = (UserVO)session.createCriteria(UserVO.class).add(Restrictions.eq("userId", userId)).setFetchMode("project", FetchMode.EAGER).uniqueResult();
            tx.commit();
        }catch(HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return user.getProjects();
    }

	public List<ProjectVO> getProjectUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	public ProjectVO getProjectDetails(ProjectVO projectVO) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProjectVO updateProject(ProjectVO projectVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
 public static void main(String args[]){
	 ProjectDAOImpl ob = new ProjectDAOImpl();
	 ProjectVO user = new ProjectVO(1, "Java", "the introduction", "Functional", "Education","2015/12/12");
	 List<ProjectVO> list = ob.getProjectUsers(1);
	 for(ProjectVO pr : list){
		 System.out.println("vo : " +pr);
	 }
	 //ProjectVO user = new ProjectVO();
	 /*ProjectDAO dao = new ProjectDAOImpl();
	 user = dao.addProject(user);
	 System.out.println(user);*/
	 
	 
 }
	

}

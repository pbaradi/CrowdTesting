package com.mtc.dao;

import java.util.List;

import com.mtc.model.BugVO;

public interface BugDAO {
	
	public BugVO addBug(BugVO bugVO);
	
	public List<BugVO> getBugs();
	
	public BugVO getBugDetails();
	
	public BugVO updateBug(BugVO bugVO);
	
	public int deleteBug(BugVO bugVO);
	
	public List<BugVO> BugByProjectID(int projectId);
	
	public List<BugVO> BugByUserID(int userId);

}

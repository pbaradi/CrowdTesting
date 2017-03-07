package com.mtc.resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mtc.manager.TesterManager;
import com.mtc.manager.impl.TesterManagerImpl;
import com.mtc.model.BugVO;
import com.mtc.model.ProjectVO;
import com.mtc.model.RestBugs;
import com.mtc.model.RestProjects;
import com.mtc.model.RestUsers;
import com.mtc.model.UserVO;

@Path("/tester")
public class TesterEndPoint {
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response testerLogin(RestUsers user){
		TesterManager mgnr = new TesterManagerImpl();
		UserVO userVO = new UserVO(user.getPassword(), user.getEmailId(),user.getUserFlag());
		userVO = mgnr.testerLogin(userVO.getEmailId(), userVO.getPassword(),userVO.getUserFlag());
		System.out.println("userVO:"+userVO);
		//UserVO user = new UserVO(user.getFirstName(),user.getLastName(),user.getPassword(),user.getEmailId(),user.getUserId(),user.getUserFlag(),user.getAmount());
		user = new RestUsers(userVO.getFirstName(), userVO.getLastName(), userVO.getPassword(), userVO.getEmailId(), userVO.getUserId(), userVO.getUserFlag(), userVO.getAmount());
		System.out.println("user:"+user);
		GenericEntity<RestUsers> entity = new GenericEntity<RestUsers>(user) {};
		return Response.status(Status.OK).entity(entity).build();
		
	}
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerTester(RestUsers user){
		TesterManager mgnr = new TesterManagerImpl();
		UserVO userVO = new UserVO(user.getFirstName(),user.getLastName(),user.getPassword(),user.getEmailId(),user.getUserId(),user.getUserFlag(),user.getAmount());
		userVO = mgnr.registerTester(userVO);
		//userVO = mgnr.registerTester(userVO);
		user = new RestUsers(userVO.getFirstName(), userVO.getLastName(), userVO.getPassword(), userVO.getEmailId(), userVO.getUserId(), userVO.getUserFlag(), userVO.getAmount());
		System.out.println("user:"+user);
		GenericEntity<RestUsers> entity = new GenericEntity<RestUsers>(user) {};
		return Response.status(Status.OK).entity(entity).build();
	}
	
	@PUT
	@Path("/calcAmount/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response evaluateIncentive(@PathParam("userId") int userId){
		TesterManager mgnr = new TesterManagerImpl();
		UserVO userVO = mgnr.evaluateIncentive(userId);
		RestUsers user = new RestUsers(userVO.getFirstName(), userVO.getLastName(), userVO.getPassword(), userVO.getEmailId(), userVO.getUserId(), userVO.getUserFlag(), userVO.getAmount());
		GenericEntity<RestUsers> entity = new GenericEntity<RestUsers>(user) {};
		return Response.status(Status.OK).entity(entity).build();
	}
	
	
	@POST
	@Path("/addBug")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submitBug(RestBugs bug){
		TesterManager mgnr = new TesterManagerImpl();
		BugVO bugVO= new BugVO(bug.getUser(),bug.getProject(),bug.getBugDescription(),bug.getBugType(), bug.getBugId());
		bugVO  = mgnr.submitBug(bugVO);
		bug= new RestBugs(bugVO.getUser(),bugVO.getProject(),bugVO.getBugDescription(),bugVO.getBugType(),bugVO.getBugId());
		GenericEntity<RestBugs> entity = new GenericEntity<RestBugs>(bug) {};
		return Response.status(Status.OK).entity(entity).build();
	}
	
	@PUT
	@Path("/updateBug")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUserBug(RestBugs bug){
		
		TesterManager mgnr = new TesterManagerImpl();
		BugVO bugVO= new BugVO(bug.getUser(),bug.getProject(),bug.getBugDescription(),bug.getBugType(), bug.getBugId());
		bugVO = mgnr.updateUserBug(bugVO);	
		bug = new RestBugs(bugVO.getUser(),bugVO.getProject(), bugVO.getBugDescription(),bugVO.getBugType(),bugVO.getBugId() );
		GenericEntity<RestBugs> entity = new GenericEntity<RestBugs>(bug) {};
		return Response.status(Status.OK).entity(entity).build();
	}
	
	@GET
	@Path("/testers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTesters(){
		TesterManager mgnr = new TesterManagerImpl();
		List<UserVO> userVOs = mgnr.getTesters();
		List<RestUsers> restUsers = new ArrayList<>();
		for (UserVO uservos : userVOs){
			RestUsers  RestUsersVO = new RestUsers(uservos.getFirstName(), uservos.getLastName(), uservos.getPassword(), uservos.getEmailId(), uservos.getUserId(), uservos.getUserFlag(), uservos.getAmount());
			restUsers .add(RestUsersVO);
		}
		GenericEntity<List<RestUsers>> entity = new GenericEntity<List<RestUsers>>(restUsers) {};
		return Response.status(Status.OK).entity(entity).build();
	}
	
	@GET
	@Path("/projects")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjects(){
		TesterManager mgnr = new TesterManagerImpl();
		List<ProjectVO> projectVOs = mgnr.getProjects();
		List<RestProjects> restProjects = new ArrayList<>();
		for (ProjectVO projectvos : projectVOs){
			RestProjects  RestProjectVO = new RestProjects(projectvos.getProjectId(),projectvos.getProjectName(),projectvos.getProjectDesc(),projectvos.getTestType(),projectvos.getProjectCategory(),projectvos.getDeadLine());
			restProjects .add(RestProjectVO);
		}
		GenericEntity<List<RestProjects>> entity = new GenericEntity<List<RestProjects>>(restProjects) {};
		return Response.status(Status.OK).entity(entity).build();
	}
	
	@GET
	@Path("/download")
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.MULTIPART_FORM_DATA)
	public Response downlaodApp(@QueryParam("projectId") int projectId){
		int userId =1 ;
		TesterManager mgnr = new TesterManagerImpl();
		File downloadFile = mgnr.downloadApp(projectId, userId);
	//	GenericEntity<UserVO> entity = new GenericEntity<UserVO>(user) {};
		//return Response.status(Status.OK).entity(entity).build();
		return Response.ok((Object) downloadFile).header("Content-Disposition",
				"attachment; filename=\""+projectId+"\"").build();
	
	
	}


}

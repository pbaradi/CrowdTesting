package com.mtc.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mtc.manager.AppManager;
import com.mtc.manager.impl.AppManagerImpl;
import com.mtc.model.BugVO;
import com.mtc.model.RestBugs;
import com.mtc.model.RestUsers;
import com.mtc.model.UserVO;

@Path("/admin")
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
public class AdminEndPoint {

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/login")
	public Response loginAdmin(RestUsers user){
		AppManager mgnr = new AppManagerImpl();
		UserVO userVO = new UserVO(user.getPassword(), user.getEmailId(),user.getUserFlag());
		userVO = mgnr.loginAdmin(userVO.getEmailId(),userVO.getPassword(),userVO.getUserFlag());
		user = new RestUsers(userVO.getFirstName(), userVO.getLastName(), userVO.getPassword(), userVO.getEmailId(), userVO.getUserId(), userVO.getUserFlag(), userVO.getAmount());
		GenericEntity<RestUsers> entity = new GenericEntity<RestUsers>(user) {};
		return Response.status(Status.OK).entity(entity).build();
	}
	
	@GET
	@Path("/bugprojectid/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response BugByProjectID(@PathParam("projectId") int projectId){
		AppManager mgnr = new AppManagerImpl();
		List<BugVO> bugVOs = mgnr.BugByProjectID(projectId);
		List<RestBugs> restBugs = new ArrayList<>();
		for (BugVO bugvos : bugVOs){
			RestBugs restbugs = new RestBugs(bugvos.getBugId(),bugvos.getBugDescription(),bugvos.getBugType());
			restBugs.add(restbugs);
		}
		GenericEntity<List<RestBugs>> entity = new GenericEntity<List<RestBugs>>(restBugs){};
		return Response.status(Status.OK).entity(entity).build();
	}
	
	@GET
	@Path("/buguserid/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response BugByUserID(@PathParam("userId") int userId){
		AppManager mgnr = new AppManagerImpl();
		List<BugVO> bugVOs = mgnr.BugByUserID(userId);
		List<RestBugs> restBugs = new ArrayList<>();
		for (BugVO bugvos : bugVOs){
			RestBugs restbugs = new RestBugs(bugvos.getBugId(),bugvos.getBugDescription(),bugvos.getBugType());
			restBugs.add(restbugs);
		}
		GenericEntity<List<RestBugs>> entity = new GenericEntity<List<RestBugs>>(restBugs){};
		return Response.status(Status.OK).entity(entity).build();
	}

	
}

package com.mtc.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.JSONException;
import org.json.JSONObject;

//import com.amazonaws.util.json.JSONException;
//import com.amazonaws.util.json.JSONObject;

import com.mtc.manager.AppManager;
import com.mtc.manager.impl.AppManagerImpl;
import com.mtc.model.ProjectVO;
import com.mtc.model.RestProjects;
import com.mtc.model.RestUsers;
import com.mtc.model.UserVO;

@Path("/client")
public class ClientEndPoint {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadApp(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("data") String fileDet) {

		JSONObject data = null;
		JSONObject innerJson = null;
		int userId = 0;
		ProjectVO projectVO = null;
		
		File temp;
		GenericEntity<UserVO> entity = null;
		try {
			data = new JSONObject(fileDet);
			userId = data.getInt("userId");
			innerJson = (JSONObject) data.get("project");
			
			projectVO = new ProjectVO(innerJson.getString("projectName"), innerJson.getString("projectDesc"),
										innerJson.getString("testType"), innerJson.getString("projectCategory"),innerJson.getString("deadline"));
			
			temp = File.createTempFile("temp-file-name", ".tmp"); //Create temp file location for file upload
			String absolutePath = temp.getAbsolutePath();
			String tempFilePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
			String uploadedFileLocation = tempFilePath + fileDetail.getFileName();
			
			writeToFile(uploadedInputStream, uploadedFileLocation);
			
			File file = new File(uploadedFileLocation);
			AppManager mgnr = new AppManagerImpl();
			UserVO userVO = mgnr.uploadApp(userId, projectVO, file);
			entity = new GenericEntity<UserVO>(userVO) {};

		} catch (Exception exp) {
			System.out.println("An error occurred while uploading the file : "	+ exp);
		}finally{
			try {
				uploadedInputStream.close();
			} catch (IOException e) {
				System.out.println("Error occurred while closing stream");
			}
		}
		return Response.status(Status.OK).entity(entity).build();
	}

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/login")
	public Response loginClient(RestUsers user) {
		AppManager mgnr = new AppManagerImpl();
		UserVO userVO = new UserVO(user.getPassword(), user.getEmailId(),
				user.getUserFlag());
		userVO = mgnr.loginClient(userVO.getEmailId(), userVO.getPassword(),
				userVO.getUserFlag());
		user = new RestUsers(userVO.getFirstName(), userVO.getLastName(),
				userVO.getPassword(), userVO.getEmailId(), userVO.getUserId(),
				userVO.getUserFlag(), userVO.getAmount());
		GenericEntity<RestUsers> entity = new GenericEntity<RestUsers>(user) {
		};
		return Response.status(Status.OK).entity(entity).build();
	}

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("/register")
	public Response registerClient(RestUsers user) {
		AppManager mgnr = new AppManagerImpl();
		UserVO userVO = new UserVO(user.getFirstName(), user.getLastName(),
				user.getPassword(), user.getEmailId(), user.getUserId(),
				user.getUserFlag(), user.getAmount());
		userVO = mgnr.registerClient(userVO);
		user = new RestUsers(userVO.getFirstName(), userVO.getLastName(),
				userVO.getPassword(), userVO.getEmailId(), userVO.getUserId(),
				userVO.getUserFlag(), userVO.getAmount());
		GenericEntity<RestUsers> entity = new GenericEntity<RestUsers>(user) {
		};
		return Response.status(Status.OK).entity(entity).build();
	}

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getClients() {
		AppManager mgnr = new AppManagerImpl();
		List<RestUsers> restuser = new ArrayList<RestUsers>();
		List<UserVO> user = mgnr.getClients();
		// Printing the values
		for (UserVO userVO : user) {
			RestUsers restusers = new RestUsers(userVO.getFirstName(),
					userVO.getLastName(), userVO.getPassword(),
					userVO.getEmailId(), userVO.getUserId(),
					userVO.getUserFlag(), userVO.getAmount());
			restuser.add(restusers);
		}
		GenericEntity<List<RestUsers>> entity = new GenericEntity<List<RestUsers>>(
				restuser) {
		};
		return Response.status(Status.OK).entity(entity).build();
	}

	@GET
	@Path("/getProjectUsers/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectUsers(@PathParam("userId") int userId) {
		AppManager mgnr = new AppManagerImpl();
		List<ProjectVO> projectVOs = mgnr.getProjectUsers(userId);
		List<RestProjects> restProject = new ArrayList<>();
		for (ProjectVO projectvos : projectVOs) {
			RestProjects restProjects = new RestProjects(
					projectvos.getProjectId(), projectvos.getProjectName(),
					projectvos.getProjectDesc(), projectvos.getTestType(),
					projectvos.getProjectCategory(), projectvos.getDeadLine());
			restProject.add(restProjects);
		}
		GenericEntity<List<RestProjects>> entity = new GenericEntity<List<RestProjects>>(
				restProject) {
		};
		return Response.status(Status.OK).entity(entity).build();
	}

	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) throws IOException {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			throw e;
		}

	}

}

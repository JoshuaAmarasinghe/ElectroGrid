package com;

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

import model.User;
import com.UserService;
import bean.UserBean; 

@Path("Users")
public class UserService {
	
	//object
	User UserObj = new User();

	//Fetch All
	@GET
	@Path("/customers")
	@Produces(MediaType.TEXT_HTML)
	public String readUserDetails(){
		return UserObj.readUserDetails();
	}
	
	//Fetch One
	@GET
	@Path("/{userId}")//view a specific user by id
	@Produces(MediaType.TEXT_HTML)
	public String UserProfileDetails(@PathParam("userId") String userId) {

		return UserObj.getUserDetails(userId);
	}

	//Register
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(String userData) {
		UserBean userBean = new UserBean ();
		userBean.convertStringToJSONInsert(userData);

		String output = UserObj.insertUser(userBean);
		return output;
	}
	
	//Update user
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData){
		UserBean userBean = new UserBean();
		userBean.convertStringToJSONUpdateUser(userData);

		String output = UserObj.updateUser(userBean);
		return output;
	}

	//Update Admin
	@PUT
	@Path("/admin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAdmin(String userData){
		UserBean userBean = new UserBean();
		userBean.convertStringToJSONUpdateUser(userData);

		String output = UserObj.updateAdmin(userBean);
		return output;
	}
	
	//Delete
	@DELETE
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(@PathParam("userId") String userId) {
		UserBean userBean = new UserBean();
		userBean.setUserId(Integer.parseInt(userId));
		
		String output = UserObj.deleteUser(userBean);
		return output;
	}
	
	@GET
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String login(String userData) {
		UserBean userBean = new UserBean ();
		userBean.convertStringToJSONlogin(userData);

		String output = UserObj.login(userBean);

		return output;
		
	}
}
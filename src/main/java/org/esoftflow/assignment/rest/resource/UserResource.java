package org.esoftflow.assignment.rest.resource;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.esoftflow.assignment.rest.errorhandling.AppException;
import org.esoftflow.assignment.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Path("/users")
public class UserResource {

	@Autowired
	private UserService userService;

	/*
	 * *********************************** CREATE ***********************************
	 */

	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response createUser(User user) throws AppException {
		Long createUserId = userService.createUser(user);
		return Response.status(Response.Status.CREATED)// 201
				.entity("A new user has been created")
				.header("Location",
						"http://localhost:8888/eSoftFlow-Assignment/users/"
								+ String.valueOf(createUserId)).build();
	}	
	

	
	@POST
	@Path("list")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response createUsers(List<User> users) throws AppException {
		userService.createUsers(users);
		return Response.status(Response.Status.CREATED) // 201
				.entity("List of users was successfully created").build();
	}

	/*
	 * *********************************** READ ***********************************
	 */
	
	@GET	 
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<User> getUsers(
			@QueryParam("orderByInsertionDate") String orderByInsertionDate,
			@QueryParam("numberDaysToLookBack") Integer numberDaysToLookBack)
			throws IOException,	AppException {
		List<User> users = userService.getUsers(
				orderByInsertionDate, numberDaysToLookBack);
		return users;
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getUserById(@PathParam("id") Long id)
			throws IOException,	AppException {
		User userById = userService.getUserById(id);
		return Response.status(200)
				.entity(userById, new Annotation[0])
				.header("Access-Control-Allow-Headers", "X-extra-header")
				.allow("OPTIONS").build();
	}
	

	/*
	 * *********************************** UPDATE ***********************************
	 */


	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response putUserById(@PathParam("id") Long id, User user)
			throws AppException {

		User userById = userService.verifyUserExistenceById(id);

		if (userById == null) {
			// resource not existent yet, and should be created under the
			// specified URI
			Long createUserId = userService.createUser(user);
			return Response
					.status(Response.Status.CREATED)
					// 201
					.entity("A new user has been created")
					.header("Location",
							"http://localhost:8888/eSoftFlow-Assignment/users/"
									+ String.valueOf(createUserId)).build();
		} else {			
			user.setId(id);
			userService.updateUser(user);
			return Response
					.status(Response.Status.OK)
					// 200
					.entity("The user you specified has been fully updated")
					.header("Location",
							"http://localhost:8888/eSoftFlow-Assignment/users/"
									+ String.valueOf(id)).build();
		}
	}

	
	@POST
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.TEXT_HTML })
	public Response partialUpdateUser(@PathParam("id") Long id,
			User user) throws AppException {
		user.setId(id);
		userService.updateUser(user);
		return Response
				.status(Response.Status.OK)
				// 200
				.entity("The user you specified has been successfully updated")
				.build();
	}

	/*
	 * *********************************** DELETE ***********************************
	 */
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.TEXT_HTML })
	public Response deleteUserById(@PathParam("id") Long id) {
		userService.deleteUserById(id);
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("User successfully removed from database").build();
	}

	@DELETE
	@Produces({ MediaType.TEXT_HTML })
	public Response deleteUsers() {
		userService.deleteUsers();
		return Response.status(Response.Status.NO_CONTENT)// 204
				.entity("All users have been successfully removed").build();
	}

	public void setuserService(UserService userService) {
		this.userService = userService;
	}

}

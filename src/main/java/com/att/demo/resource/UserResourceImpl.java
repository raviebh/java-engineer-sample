package com.att.demo.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.att.demo.exception.CustomError;
import com.att.demo.model.User;
import com.att.demo.service.UserService;


@Controller
public class UserResourceImpl implements UserResource {

	public static final Logger logger = LoggerFactory.getLogger(UserResourceImpl.class);

	public static final String ERR_CODE_EXISTING_USER = "ERR1";

	public static final String ERR_MESSAGE_EXISTING_USER = "Unable to create user. User with name already exists";

	public static final String ERR_CODE_USER_NOT_FOUND = "ERR2";

	public static final String ERR_MESSAGE_USER_NOT_FOUND = "User not found for id: ";

	@Autowired
	UserService userService; // Service which will do all data
								// retrieval/manipulation work

	private static String baseUrl = "/users";

	/**
	 * Creates a new user from user object
	 * Returns: 400 if validation fails, 201 on success and 409 if user already exists
	 */
	@Override
	public Response createUser(User user) {
		Link link = Link.fromUri(baseUrl).rel("self").build();
		if (!userService.isExistingUser(Long.toString(user.getId()))) {
			List<CustomError> errorMessages = new ArrayList<CustomError>(0);
			userService.validateUser(user, errorMessages);
			if (!errorMessages.isEmpty()) {
				return Response.status(HttpStatus.SC_BAD_REQUEST).links(link).entity(errorMessages).build();
			}
			userService.addUser(user);
			logger.info("Succesfully created user with id: "+user.getId());
			return Response.status(HttpStatus.SC_CREATED).links(link).build();
		} else {
			logger.info("Error creating user with id: "+user.getId());
			return Response.status(HttpStatus.SC_CONFLICT).links(link)
					.entity(new CustomError(ERR_MESSAGE_EXISTING_USER, ERR_CODE_EXISTING_USER)).build();
		}
	}
	
	

	/**
	 * Retrieves an existing user with id.
	 * returns 404 if user doesn't exist
	 */
	@Override
	public Response findUserById(String id) {
		User user = userService.findById(id);
		if (user == null) {
			return Response.status(HttpStatus.SC_NOT_FOUND)
					.entity(new CustomError(ERR_MESSAGE_EXISTING_USER, ERR_CODE_EXISTING_USER)).build();
		}
		Link link = Link.fromUri(baseUrl).rel("self").build();
		return Response.ok(user).build();
	}
	
}

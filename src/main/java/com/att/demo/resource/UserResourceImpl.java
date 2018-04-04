package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.att.demo.exception.CustomError;
import com.att.demo.model.User;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.UserService;

@Controller
public class UserResourceImpl implements UserResource {

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work

	private static String baseUserUrl = "/users";

	@Override
	public Response findAllIUsers() {
		List<User> users = userService.findAllUsers();		
		if (users == null) {
			return Response.noContent().build();
		}		
		Link link = Link.fromUri(baseUserUrl).rel("self").build();		
		ResourceCollection<User> resource = new ResourceCollection<>(users);
		return Response.ok(resource).links(link).build();
	}
	
	@Override
	public Response findUserById(long id) {
		User user = userService.findById(id);
		
		if(user == null) {
			return Response.status(404).type("application/json").entity(new CustomError("User with id " + id +" not found", "NOT_FOUND")).build();
		}
		return Response.ok(user).build();
	}

	@Override
	public Response createUser(User user) {
		boolean exists = userService.isUserExist(user);
		
		if(exists) {
			return Response.status(409).type("application/json").entity(new CustomError("Unable to create. A User with name already exist", "CONFLICTED")).build();
		}
		userService.saveUser(user);
		return Response.status(201).type("text/plain").entity("User created!").build();
	}

}

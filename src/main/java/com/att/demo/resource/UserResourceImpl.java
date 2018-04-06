package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.att.demo.model.User;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.UserService;

@Controller
public class UserResourceImpl implements UserResource {

	public static final Logger logger = LoggerFactory.getLogger(UserResourceImpl.class);

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work

	private static String baseUrl = "/users";

	@Override
	public Response getUsers() {
		List<User> users = userService.findAllUsers();		
		if (users == null) {
			return Response.noContent().build();
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		ResourceCollection<User> resource = new ResourceCollection<>(users);
		return Response.ok(resource).links(link).build();
		//return null;
	}

	@Override
	public User createUser() {
		// TODO Auto-generated method stub
		return null;
	}

	
}

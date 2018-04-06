package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.att.demo.model.Account;
import com.att.demo.model.User;
import com.att.demo.model.representation.Resource;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.AccountService;
import com.att.demo.service.UserService;

@Controller
public class UserResourceImpl implements UserResource {
	
	@Autowired
	UserService userService;

	private static String baseUrl = "/users";
	@Override
	public Response getUsers() {
		List<User> users = userService.getUsers();		
		if (users == null) {
			return Response.noContent().build();
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		ResourceCollection<User> resource = new ResourceCollection<>(users);
		return Response.ok(resource).links(link).build();
	}
	@Override
	public Response addUser(User user) {
		User newUser = userService.createUser(user);		
		if (newUser == null) {
			return Response.noContent().build();
		}		
		Link link = Link.fromUri(baseUrl).rel("self").build();		
		Resource<User> resource = new Resource<>(newUser);
		return Response.ok(resource).links(link).build();
	}


	
}

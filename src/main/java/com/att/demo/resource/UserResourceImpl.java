package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.att.demo.model.Account;
import com.att.demo.model.User;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.UserService;

@Controller
public class UserResourceImpl implements UserResource {
	
	@Autowired
	private UserService userService ;
	private static String baseUrl = "/users";

	@Override
	public Response findUsersWithAccountId(long accountId) {
		// TODO Auto-generated method stub
		List<User> users= userService.getUsersByAccountId(accountId);
		if(users==null||users.isEmpty()) {
			return Response.noContent().build();
		}
		Link link = Link.fromUri(baseUrl).rel("self").build();
		ResourceCollection<User> resource = new ResourceCollection<>(users);
		return Response.ok(resource).links(link).build();
	}

	@Override
	public Response saveUser(User user) {
		// TODO Auto-generated method stub
		if(user==null) {
    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity("User input is null").build();
    	}
		userService.saveUser(user);
		Link link = Link.fromUri(baseUrl).rel("self").build();
    	return Response.status(Response.Status.CREATED).links(link).build();
	}

	
}

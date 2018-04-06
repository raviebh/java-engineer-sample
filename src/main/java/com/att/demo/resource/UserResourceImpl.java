package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	public Response findAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users == null) {
			return Response.noContent().build();
		}
		Link link = Link.fromUri(baseUrl).rel("self").build();
		ResourceCollection<User> resource = new ResourceCollection<>(users);
		return Response.ok(resource).links(link).build();
	}
	@Override
	public Response getUserById(long id) {
		User user = userService.findById(id);
		if (user == null) {
			return Response.status(Status.NOT_FOUND).entity("Unable to find an user with id " + id).build();
		}
		Link link = Link.fromUri(baseUrl).rel("self").build();
		Resource<User> resource = new Resource<>(user);
		return Response.ok(resource).links(link).build();
	}

	
}

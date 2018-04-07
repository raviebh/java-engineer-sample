package com.att.demo.resource;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.att.demo.model.User;
import com.att.demo.model.representation.Resource;
import com.att.demo.model.representation.ResourceCollection;
import com.att.demo.service.UserService;
/*
 * This is the Controller class for User mService
 */
@Controller
public class UserResourceImpl implements UserResource {
	@Autowired
	private UserService userService;
	private static String baseUrl = "/users";
	private static final AtomicLong counter = new AtomicLong(System.currentTimeMillis() * 1000);

	@Override
	public Response findAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users == null) {
			return Response.noContent().build();
		}
		Link link = Link.fromUri(baseUrl).rel("self").build();
		ResourceCollection<User> resource = new ResourceCollection<>(users);
		return Response.status(Response.Status.OK).entity(resource).links(link).build();
	}

	@Override
	public Response getUserById(long id) {
		User user = userService.findById(id);
		if (user == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Unable to find an user with id " + id).build();
		}
		Link link = Link.fromUri(baseUrl).rel("self").build();
		Resource<User> resource = new Resource<>(user);
		return Response.status(Response.Status.OK).entity(resource).links(link).build();
	}

	@Override
	public Response createUser(User user) {
		String userName = user.getName();
		long id = counter.incrementAndGet();
		int age = user.getAge();
		int userAccountId = user.getAccountId();
		User newUser = new User();
		newUser.setName(userName);
		newUser.setId(id);
		newUser.setAccountId(userAccountId);
		newUser.setAge(age);
		userService.saveUser(newUser);
		Link link = Link.fromUri(baseUrl).rel("self").build();
		Resource<User> resource = new Resource<>(newUser);
		return Response.status(Response.Status.CREATED).entity(resource).links(link).build();
	}
}

	

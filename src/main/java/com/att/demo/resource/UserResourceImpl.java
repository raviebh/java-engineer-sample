package com.att.demo.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.att.demo.model.User;
import com.att.demo.service.UserService;



@Controller
public class UserResourceImpl implements UserResource {
	
	@Autowired
    UserService userService;
	
	
    private static String baseUrl = "/users";
    
    
    
    public Response addUser(User newUser) {
		ResponseBuilder respBuilder;
		userService.createUser(newUser);
		respBuilder = Response.status(Status.CREATED);
		respBuilder.entity(newUser);
		return respBuilder.build();
	}

	
    
    public Response getUser( String id) {
		ResponseBuilder respBuilder;
		User user = userService.getUser(id);
		respBuilder = Response.status(Status.OK);
		respBuilder.entity(user);
		return respBuilder.build();
	}


	
}

package com.att.demo.resource;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.att.demo.service.UserService;

@Controller
public class UserResourceImpl implements UserResource {

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work

	private static String baseUrl = "/users";

	/*
	@Override
	public Response findUserBasedOnId(String userId) {
		return Response.ok().build();
	}
	*/
	
}

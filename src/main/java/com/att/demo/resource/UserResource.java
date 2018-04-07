package com.att.demo.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.att.demo.model.User;
import com.att.demo.model.swagger.AccountResponse;
import com.att.demo.model.swagger.UserResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This is the Interface definition for User Service
 * 
 * 
 */

@Api("user")
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public interface UserResource {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get user Resource",
			notes = "Returns all the users in ResourceCollection representation format",
			response = UserResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content")					
					})
	public Response findAllIUsers();
   
    /**
     * Service definition which returns account based on the given Id
     *
     * 
     * @return User - Returns the account details for the given Id
     */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Create User Resource",
			notes = "Create User Resource when the user does not exist",
			response = UserResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "User Created"),
					@ApiResponse(code = 204, message = "No Content"),	
					@ApiResponse(code = 404, message = "User does not exist")	
					})
	public Response createUser(User user);
	
	
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get User Resource by user Id",
			notes = "Returns User details based on Id",
			response = UserResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content"),	
					@ApiResponse(code = 404, message = "Account does not exist")	
					})
	public Response findUserById(@PathParam("id") long id);
	
	
}
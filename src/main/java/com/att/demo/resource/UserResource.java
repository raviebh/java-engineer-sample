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
import com.att.demo.model.swagger.UserResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This is the Interface definition for Account mService
 * 
 * 
 */
/**
 * This is the Interface definition for User Resource
 * 
 */
@Api("user")
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public interface UserResource {
	
	/**
     * Service definition which returns all the accounts
     * @return User - Returns the details of the accounts being searched
     */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Create New User",
			notes = "Creates a new User",
			response = Response.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "CREATED"),
					@ApiResponse(code = 409, message = "Conflict! User already exists"),
					@ApiResponse(code = 400, message = "Bad Request")					
					})
	public Response createUser(User user);
	
	/**
     * Service definition which returns all the accounts
     * @return User - Returns the details of the accounts being searched
     */
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Account Resource",
			notes = "Returns all the accounts in ResourceCollection representation format",
			response = UserResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 404, message = "User not found")					
					})
	public Response findUserById(@PathParam("id") String id);
	
}
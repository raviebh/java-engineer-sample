package com.att.demo.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.att.demo.model.Account;
import com.att.demo.model.User;
import com.att.demo.model.swagger.AccountResponse;
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
@Api("user")
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public interface UserResource {
	
	@GET
	@Path("/{accountId}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get User Resource",
			notes = "Returns users with same account id",
			response = UserResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content")					
					})
	public Response findUsersWithAccountId(@PathParam("accountId")long accountId);
	
	
	    @POST
	    @Produces({MediaType.APPLICATION_JSON})
		@Consumes({MediaType.APPLICATION_JSON})
	    @ApiOperation(
				value = "Inserts an user onto the collection",
				notes = "adds the user"
			    )
		@ApiResponses(
				value = {
						@ApiResponse(code = 201, message = "CREATED"),
						@ApiResponse(code = 400, message = "BAD_REQUEST")	
						})
	    public Response saveUser (User user);
}
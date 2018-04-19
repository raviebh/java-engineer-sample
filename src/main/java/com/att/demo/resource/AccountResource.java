package com.att.demo.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.att.demo.model.Account;
import com.att.demo.model.User;
import com.att.demo.model.swagger.AccountResponse;
import com.att.demo.model.swagger.UserResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * This is the Interface definition for Account mService
 * 
 * 
 */
@Api("account")
@Path("/accounts")
@Produces({MediaType.APPLICATION_JSON})
public interface AccountResource {
	
    /**
     * Service definition which returns all the accounts
     *
     * 
     * @return User - Returns the details of the accounts being searched
     */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Account Resource",
			notes = "Returns all the accounts in ResourceCollection representation format",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content")					
					})
	public Response findAllAccounts();
	
	
	/**
     * Service definition which returns all the accounts
     * @return User - Returns the details of the accounts being searched
     */
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Create New account",
			notes = "Creates a new account",
			response = Response.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "CREATED"),
					@ApiResponse(code = 409, message = "Conflict! account already exists"),
					@ApiResponse(code = 400, message = "Bad Request")					
					})
	public Response createAccount(Account account);
	
	/**
     * Service definition which returns account for given id
     * @return User - Returns the details of the accounts being searched
     */
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Account Resource",
			notes = "Returns the account for the given id",
			response = UserResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 404, message = "Account not found")					
					})
	public Response findAccountById(@PathParam("id") Long id);
	

	
}
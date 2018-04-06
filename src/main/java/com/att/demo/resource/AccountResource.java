package com.att.demo.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.att.demo.model.Account;
import com.att.demo.model.swagger.AccountResponse;


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
    * Service definition which returns account for given id
    *
    * 
    * @return User - Returns the details of the account being searched
    */
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Account Resource",
			notes = "Returns the Account for a given accountId in ResourceCollection representation format",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 404, message = "NOT_FOUND")
					})
	@Path("{accountId}")
	public Response getAccount(@PathParam(value = "accountId") String accountId);
	
	/**
	    * Service definition for creating account
	    *
	    * 
	    * @return User - Returns the details of the account being searched
	    */
		@POST
		@Consumes({MediaType.APPLICATION_JSON})
		@ApiOperation(
				value = "Create Account Resource",
				notes = "Returns 201 201 if successthe Account for a given accountId in ResourceCollection representation format",
				response = AccountResponse.class)
		@ApiResponses(
				value = {
						@ApiResponse(code = 200, message = "OK"),
						@ApiResponse(code = 404, message = "NOT_FOUND")
						})
		
	public Response createAccount(Account account);
	
	
}
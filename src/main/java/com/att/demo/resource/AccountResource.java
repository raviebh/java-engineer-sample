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
import com.att.demo.model.swagger.AccountResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
     * Service definition which returns account based on the given Id
     *
     * 
     * @return User - Returns the account details for the given Id
     */
	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Account Resource by account Id",
			notes = "Returns account details based on Id",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content"),	
					@ApiResponse(code = 404, message = "Account does not exist")	
					})
	public Response findAccountById(@PathParam("id") long id);
	
	
	
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
			value = "Create Account Resource",
			notes = "Create Account Resource when the user does not exist",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "Account Created"),
					@ApiResponse(code = 204, message = "No Content"),	
					@ApiResponse(code = 404, message = "Account does not exist")	
					})
	public Response createAccount(Account account);
	
	
	@GET
	@Path("/{id}/users")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get User details based on the given account Id",
			notes = "Get User details based on the given account Id",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content"),	
					@ApiResponse(code = 404, message = "Account does not exist")	
					})
	public Response findAccountByAndUsers(@PathParam("id") long id);
	
}
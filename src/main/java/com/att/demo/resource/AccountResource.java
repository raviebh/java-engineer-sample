package com.att.demo.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

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
	@Path("/findAllAccounts")
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

	@POST
	@Path("/createAccount")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Create Account Resource",
			notes = "Create the account in ResourceCollection representation format",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 201, message = "No Content"),
					@ApiResponse(code = 209, message = "Unable to create. A Account with name already exist")					
			})
	public Response createAccount(@RequestBody Account account);
	
	@GET
	@Path("/getAccount/{acconutId}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	@ApiOperation(
			value = "Get Account Resource By Account Id",
			notes = "Returns all the accounts in ResourceCollection Related to same ID",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 201, message = "Created"),
					@ApiResponse(code = 404, message = "No Content")					
					})
	public Response getAccount(@PathParam("acconutId")long acconutId);	
}
package com.att.demo.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import static org.springframework.http.ResponseEntity.ok;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.att.demo.model.Account;
import com.att.demo.model.swagger.AccountResponse;
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
	@RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(
			value = "Get Account Resource",
			notes = "Returns all the accounts in ResourceCollection representation format",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content")					
					})
	@ResponseBody
	public Response findAllAccounts();
	
	

	@RequestMapping(value = "/{id}",
	            method = RequestMethod.GET,
	            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(
			value = "Get a specific Account Resource",
			notes = "Returns a specific accounts in ResourceCollection representation format",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content")					
					})
	public Response findAccountId(@PathVariable String id);
	
	@RequestMapping(value = "",
				method = RequestMethod.POST,
				consumes = {"application/json"},
				produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(
			value = "Get a specific Account Resource",
			notes = "Returns a specific accounts in ResourceCollection representation format",
			response = AccountResponse.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "OK"),
					@ApiResponse(code = 204, message = "No Content")					
	})
    public Response createAccount(@RequestBody AccountRequest account);
	
	
}
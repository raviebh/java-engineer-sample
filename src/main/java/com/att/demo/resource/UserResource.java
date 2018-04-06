package com.att.demo.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
@Api("users")
@Path("/users")
@Produces({MediaType.APPLICATION_JSON})
public interface UserResource {
	

	/*
	@RequestMapping(value = "/{id}",
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
	public Response findUserBasedOnId(@PathVariable String id);
	*/

	
}
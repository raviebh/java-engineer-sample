package com.att.demo.resource;

import com.att.demo.model.User;
import com.att.demo.model.swagger.AccountResponse;
import com.att.demo.model.swagger.UserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This is the Interface definition for Account mService
 * 
 * 
 */
@Api("user")
@Path("/users")
public interface UserResource {

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
    public Response findAllUsers();


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(
            value = "Get Account Resource",
            notes = "Returns all the accounts in ResourceCollection representation format",
            response = UserResponse.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "OK"),
                    @ApiResponse(code = 404, message = "No Content Found")

            })

    @Path(value = "/{id}")
    public Response getUser(@PathParam("id")  Integer id);


    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @ApiOperation(
            value = "Get Account Resource",
            notes = "Returns all the accounts in ResourceCollection representation format",
            response = UserResponse.class)
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 409, message = "CONFLICTED")

            })

    public Response createUser(@RequestBody User user);
	
   
	
}
package com.att.demo.resource;

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

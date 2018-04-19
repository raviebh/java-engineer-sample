package com.att.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.att.demo.exception.CustomError;
import com.att.demo.model.User;
import com.att.demo.service.UserService;

import io.swagger.annotations.*;


@RestController
@RequestMapping("/user")
@Api(value="User Resource")
public class UserResourceController {
	
	@Autowired
	UserService userService;
	
	@ApiOperation(value = "Add a User")
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Succesful Addition of User"),
					@ApiResponse(code = 204, message = "User information not found"),
					@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
					@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")	
					})
	@PostMapping(path ="/createUser", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUser(@RequestBody final User user) {	
		CustomError status = null;
		userService.saveUser(user);		
		if (userService.isUserExist(user)) {			
			status = new CustomError("Unable to create. A User with name " +user.getName()  + " already exist", HttpStatus.CONFLICT.toString());
			return new ResponseEntity<CustomError>(status, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("User Succesfully created", HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Find All Users",response = User.class)
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Sucessful retrieval of All Users information"),
					@ApiResponse(code = 204, message = "Users Details not found"),
					@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
					@ApiResponse(code = 404, message = "The resource you were trying to reach is not found")	
					})
	@GetMapping("/findAllUsers")
	public ResponseEntity<?> findAllUsers() {		
		List<User> users =  userService.findAllUsers();		
		if (users == null || users.isEmpty()) {			
			CustomError status = new CustomError(HttpStatus.NO_CONTENT.getReasonPhrase(), HttpStatus.NO_CONTENT.toString());
			status.setErrorCode(HttpStatus.NO_CONTENT.toString());			
			return new ResponseEntity<CustomError>(status, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

}

package com.att.demo.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.att.demo.model.User;
import com.att.demo.model.representation.ResourceCollection;

/**
 * This is the Interface definition for Account mService
 * 
 * 
 */
public interface UserResource {
	
	@GetMapping("/getusers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<ResourceCollection<User>> getUsers();
	
	@PostMapping("/addusers")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<User> createUser(@RequestBody User usr);
	
	@GetMapping("/finduser/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<User> findbyUserId(@PathVariable int id);
	
}
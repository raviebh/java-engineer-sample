package com.att.demo.resource;

import com.att.demo.model.User;

/**
 * This is the Interface definition for Account mService
 * 
 * 
 */
public interface UserResource {
	
	public User getUsers();
	
	public User createUser();
	
   
	
}
package com.att.demo.model.swagger;

import com.att.demo.model.User;
import com.att.demo.model.representation.Resource;

/**
 * Swagger response class for the User model object.
 * @param <T>
 * @param <T>
 */
public class UserResponse extends Resource<User> {
	
    /**
     * This constructor sets the content of the User Response 
     * @param content Command line parameters if any
     * 
     */
	public UserResponse(User content) {
		super(content);
	}

    /**
     * This method gets the content of the User Response 
     * @return content of the user Response
     * 
     */
	@Override
	public User getContent() {
		return super.getContent();
	}
}
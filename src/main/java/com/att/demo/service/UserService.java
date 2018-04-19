package com.att.demo.service;
import com.att.demo.exception.CustomError;
import com.att.demo.model.User;

public interface UserService {
	
	User findById(String id);
	
	boolean isExistingUser(String id);
	
	void addUser(User user);
	
	void validateUser(User user, java.util.List<CustomError> errorMessages);
	
}
package com.att.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.att.demo.exception.CustomError;
import com.att.demo.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
public static final String ERR_CODE_INVALID_ADD_REQUEST= "ERR3";
	
public static final String ERR_MESSAGE_INVALID_ADD_REQUEST= "Invalid post body";
	
public static final String ERR_CODE_MISSING_ID= "ERR4";
	
public static final String ERR_MESSAGE_MISSING_ID= "Missing required value for user id";
	
public static final String ERR_CODE_MISSING_ACCOUNT_ID = "ERR5";
	
public static final String ERR_MESSAGE_MISSING_ACCOUNT_ID= "Missing required value for user account id";
	
public static final String ERR_CODE_MISSING_NAME= "ERR6";
	
public static final String ERR_MESSAGE_MISSING_NAME= "Missing reuired value for user name";
	
private static Map<String, User> users;
	
	
	@Override
	public User findById(String id) {
		if(users != null && users.containsKey(id)) {
			return users.get(id);
		}
		return null;
	}
	
	@Override
	public boolean isExistingUser(String id) {
		if(users == null || !users.containsKey(id)) {
			return false;
		} 
		return true;
	}
	
	@Override
	public void addUser(User user) {
		if(users == null) {
			users = new HashMap<String, User>(0);
		}
		users.put(Long.toString(user.getId()), user);
	}
	
	@Override
	public void validateUser(User user, List<CustomError> errorMessages) {
		if (user == null) {
			errorMessages.add(new CustomError(ERR_MESSAGE_INVALID_ADD_REQUEST, ERR_CODE_INVALID_ADD_REQUEST));
		} else {
			if (user.getId() == 0L) {
				errorMessages.add(new CustomError(ERR_MESSAGE_MISSING_ID, ERR_CODE_MISSING_ID));
			}
			if (user.getAccountId() == 0L) {
				errorMessages.add(new CustomError(ERR_MESSAGE_MISSING_ACCOUNT_ID, ERR_CODE_MISSING_ACCOUNT_ID));
			}
			if (user.getName() == null) {
				errorMessages.add(new CustomError(ERR_MESSAGE_MISSING_NAME, ERR_CODE_MISSING_NAME));
			}
		}
	}
}
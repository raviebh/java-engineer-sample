package com.att.demo.service;


import java.util.List;

import com.att.demo.model.User;

public interface UserService {
	
	
	User createUser(User user);
	User findUser(long accountId);
	List<User> getUsers();
	
	
}

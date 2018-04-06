package com.att.demo.service;


import java.util.List;

import com.att.demo.model.Account;
import com.att.demo.model.User;

public interface UserService {
	
	String createUser(User user);
	List<User> findAllUsers();
	List<User> getUsers();

	
}

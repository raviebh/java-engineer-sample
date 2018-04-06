package com.att.demo.service;

import java.util.List;

import com.att.demo.model.User;

public interface UserService {
	
void saveUser(User user);

	List<User> fetchAllUsers();
}

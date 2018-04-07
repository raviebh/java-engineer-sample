package com.att.demo.service;

import java.util.List;

import com.att.demo.model.User;

public interface UserService {

	User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	List<User> findAllUsers();
	
	boolean isUserExist(User user);
	
	public List<User> findAccountByAndUsers(long id);
}
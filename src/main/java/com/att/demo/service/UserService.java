package com.att.demo.service;

import java.util.List;

import com.att.demo.model.User;

public interface UserService {

	public List<User> getUsers();
	public User createUser(User usr,int accountId);
	public User findbyUserId(int id);
}

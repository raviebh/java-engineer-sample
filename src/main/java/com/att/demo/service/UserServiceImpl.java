package com.att.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.att.demo.model.Account;
import com.att.demo.model.User;



@Service("userService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateUserAccounts();
	}	

	public User getUser(long userId) {
		for(User user : users){
			if(user.getId() == userId){
				return user;
			}
		}

		return null;
	}
	
	public String createUser(User user) {
		counter.incrementAndGet();
		users.add(user);
		return ("201 (Success) - User added");
	}
	
	public List<User> findAllUsers() {
		return users;
	}
	
	private static List<User> populateUserAccounts(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"User1", 25, 1234));
		users.add(new User(counter.incrementAndGet(),"User2", 26, 1235));
		users.add(new User(counter.incrementAndGet(),"User3", 27, 1236));
		return users;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.att.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.att.demo.model.User;



@Service("UserService")
public class UserServiceImpl implements UserService{
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	public User findByName(String name) {
		for(User account : users){
			if(account.getName().equalsIgnoreCase(name)){
				return account;
			}
		}
		return null;
	}
	
	public User saveUser(User user) {
		counter.incrementAndGet();
		users.add(user);
		return user;
	}

		
	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}
	
	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"User1", 1, 1));
		users.add(new User(counter.incrementAndGet(),"User2",  1, 1));
		users.add(new User(counter.incrementAndGet(),"User3",  1, 1));
		return users;
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}
	
}

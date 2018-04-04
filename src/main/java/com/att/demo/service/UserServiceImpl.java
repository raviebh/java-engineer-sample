package com.att.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.att.demo.model.User;

@Service("userService")
public class UserServiceImpl  implements UserService {

	private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	@Override
	public List<User> findAccountByAndUsers(long id) {
		List<User> userList = new ArrayList<User>();
		for(User user : users){
			if(user.getAccountId() == id){
				userList.add(user);
			}
		}
		return userList;
	}
	
	@Override
	public User findById(long id) {
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	@Override
	public User findByName(String name) {
		for(User user : users){
			if(user.getName().equalsIgnoreCase(name)){
				return user;
			}
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
		long id = counter.incrementAndGet();
		user.setId(id);
		users.add(user);
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}
	
	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"User1", 20, 1));
		users.add(new User(counter.incrementAndGet(),"User2", 20, 2));
		return users;
	}


}

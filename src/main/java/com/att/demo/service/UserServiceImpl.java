/**
 * 
 */
package com.att.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.att.demo.model.Account;
import com.att.demo.model.User;

/**
 * @author vinodbottu
 *
 */
public class UserServiceImpl implements UserService {
private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	static{
		users= populateDummyUsers();
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	private static List<User> populateDummyUsers(){
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(),"User1", 20, 1));
		users.add(new User(counter.incrementAndGet(),"User2", 21, 2));
		users.add(new User(counter.incrementAndGet(),"User3", 22, 3));
		return users;
	}

}

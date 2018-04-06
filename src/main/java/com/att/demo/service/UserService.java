/**
 * 
 */
package com.att.demo.service;

import java.util.List;

import com.att.demo.model.Account;
import com.att.demo.model.User;

/**
 * @author
 *
 */
public interface UserService {
	User findById(long id);

	User findByName(String name);

	void saveUser(User user);

	List<User> findAllUsers();
}

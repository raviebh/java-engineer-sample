package com.att.demo.service;

import java.util.List;

import com.att.demo.model.User;

public interface UserService {
  public List<User> getUsersByAccountId(long id);
  public void saveUser(User user);
}

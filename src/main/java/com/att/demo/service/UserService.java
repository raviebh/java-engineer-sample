package com.att.demo.service;

import com.att.demo.model.User;

/**
 * Created by xy79 on 4/19/2018.
 */
public interface UserService {

    User getUser(Long id);
    void createUser(User user);
}

package com.att.demo.service;

import com.att.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xy79 on 4/19/2018.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @

    private static Map<Long, User> userMap = new HashMap<>();
    static{
        userMap.put(1l,new User(1,"a",20,1 ));
        userMap.put(2l,new User(2,"b",20,1 ));
        userMap.put(3l,new User(3,"c",20,1 ));
        userMap.put(4l,new User(4,"d",20,1 ));
    }


    @Override
    public User getUser(Long id) {

        logger.info("**************** service **************");

        if(userMap.containsKey(id)){
            return userMap.get(id);
        }
        return null;
    }

    @Override
    public void createUser(User user) {
        logger.info("**************** service **************");

        userMap.put(user.getId(),user);
    }



}

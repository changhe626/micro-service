package com.example.user.service.impl;

import com.example.user.dao.UserDao;
import com.example.user.entity.UserInfo;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public UserInfo findByOpenid(String openid) {
        return userDao.findByOpenid(openid);
    }
}

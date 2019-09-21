package com.example.user.service;

import com.example.user.entity.UserInfo;

public interface UserService {

    UserInfo findByOpenid(String openid);

}

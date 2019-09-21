package com.example.user.dao;

import com.example.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao  extends JpaRepository<UserInfo,String> {

    UserInfo findByOpenid(String openid);

}

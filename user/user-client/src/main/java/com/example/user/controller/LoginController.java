package com.example.user.controller;


import com.example.common.util.CookieUtil;
import com.example.common.util.ResultVoUtil;
import com.example.common.vo.ResultVo;
import com.example.common.user.Constants;
import com.example.common.user.RoleEnum;
import com.example.user.entity.UserInfo;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 买家
     */
    @GetMapping("buyer")
    public ResultVo buyer(@RequestParam("openid") String openid,
                          HttpServletResponse response) {
        //1.openid 和数据库的匹配
        UserInfo info = userService.findByOpenid(openid);
        if (info == null) {
            return ResultVoUtil.fail("登陆失败");
        }
        //2.判断角色
        if (RoleEnum.SELLER.getCode().equals(info.getRole())) {
            return ResultVoUtil.fail("角色权限有误");
        }
        //3.设置cookie
        CookieUtil.createCookie(response, Constants.openid, openid, Constants.expire);
        return ResultVoUtil.success();
    }


    /**
     * 卖家
     */
    @GetMapping("seller")
    public ResultVo seller(@RequestParam("openid") String openid,
                           HttpServletResponse response,
                           HttpServletRequest request) {
        //判断是否已登陆
        //cookie 的验证, 以及redis中的验证
        Cookie cookie = CookieUtil.getCookie(request, Constants.token);
        Object o = stringRedisTemplate.opsForHash().get(Constants.redis_key, cookie.getValue());
        if (cookie != null && o != null) {
            return ResultVoUtil.success();
        }
        //1.openid 和数据库的匹配
        UserInfo info = userService.findByOpenid(openid);
        if (info == null) {
            return ResultVoUtil.fail("登陆失败");
        }
        //2.判断角色
        if (RoleEnum.BUYER.getCode().equals(info.getRole())) {
            return ResultVoUtil.fail("角色权限有误");
        }
        //设置到redis中去
        String token = "token_" + UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put(Constants.redis_key, token, openid);

        //3.设置cookie
        CookieUtil.createCookie(response, Constants.token, token, Constants.expire);
        return ResultVoUtil.success();
    }


}

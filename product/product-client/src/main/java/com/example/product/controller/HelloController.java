package com.example.product.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zk
 * @Description: 测试方法
 * @date 2019-09-18 10:30
 */
@RestController
public class HelloController {


    /**
     * 为了让这个方法能够跨域,添加注解
     * allowCredentials="true"  允许cookie 跨域
     * @return
     */
    @CrossOrigin(allowCredentials="true")
    @GetMapping("hello")
    public String hello() {
        return "hello world, this is the product service";
    }

}

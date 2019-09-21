package com.example.zuulserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulServerApplication.class, args);
    }
    /**
     * nginx+lua
     * Kong(商业)
     * Tyk(go开发的)
     * Zuul
     */

    //http://localhost:9000/product-service/product/list
    //                      服务名             url


    /**
     * 前置  pre
     * 限流   鉴定权限   参数校验  请求转发
     *
     * 后置  post
     * 统计  日志
     *
     *  zuul的高可用. 注册多个zuul的节点.
     */
}

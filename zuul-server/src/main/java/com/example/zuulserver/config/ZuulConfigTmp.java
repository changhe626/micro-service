package com.example.zuulserver.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Component;


//@Component   注释了, 暂时不做动态路由
public class ZuulConfigTmp {

    /**
     * 路由的动态设置.
     */
    /*@ConfigurationProperties("zuul")
    @RefreshScope
    public ZuulProperties zuulProperties(){
        ZuulProperties properties = new ZuulProperties();
        return properties;
    }*/

}

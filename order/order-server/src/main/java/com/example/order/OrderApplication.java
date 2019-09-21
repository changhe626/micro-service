package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//启用feign
@EnableFeignClients(basePackages = "com.example.product.client")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 其实下单的流程完全可以实现全部的异步化,但是这就涉及到业务了.
     * 业务的取舍,一定要有数据的一致性,以及错误的回滚,错误的记录等,事务处理等
     */
}

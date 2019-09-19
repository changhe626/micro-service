package com.example.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

    /**
     * http://localhost:8083/order-1.properties
     *
     * /{name}-{profiles}.properties
     * /{label}/{name}-{profiles}.properties
     * name 服务名
     * profiles 环境
     * label  分支branch
     */
}

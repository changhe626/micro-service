package com.example.order.config;

import com.example.order.constans.MessageConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author zk
 * @Description: 已经废弃,现在使用feign进行应用之间的通信
 * @date 2019-09-18 10:44
 */
@Component
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate template = new RestTemplate();
        return template;
    }

    /**
     * 注入一个queue
     */
    @Bean
    public Queue queue(){
        return new Queue(MessageConstant.QUEUE_NAME,true);
    }

}

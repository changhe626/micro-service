package com.example.order.controller;

import com.alibaba.fastjson.JSON;
import com.example.order.entity.OrderMaster;
import com.example.order.service.OrderService;
import com.example.product.client.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


/**
 * @author zk
 * @Description:
 * @date 2019-09-18 10:32
 */
@RefreshScope   //config 刷新配置.
@RestController
public class GetHelloController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("get-hello")
    public String  getHello() {
        //第一种方式
        //耦合性太高了,不大可能知道ip地址,以及端口号, 还有涉及到多个服务的问题
        /*RestTemplate template = new RestTemplate();
        String object = template.getForObject("http://localhost:8081/hello", String.class);
        System.out.println(object);
        return object*/

        //第二种,利用LoadBalancerClient,利用应用名字获取
        //注册在eureka中的服务名字
        /*ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT-SERVICE");
        String format = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/hello";
        RestTemplate template = new RestTemplate();
        String object = template.getForObject(format, String.class);
        System.out.println(object);
        return object;*/

        //第三种方式,使用RestTemplateConfig ,利用LoadBalanced 使用名字
        String object = restTemplate.getForObject("http://PRODUCT-SERVICE/hello", String.class);
        System.out.println(object);
        return object;
    }


    @Autowired(required = false)
    private ProductList productList;

    @GetMapping("get-hello2")
    public String getMsg(){
        String message = productList.productMessage();
        System.out.println(message);
        return message;
    }

    @Autowired
    private OrderService orderService;

    @GetMapping("testConfig")
    public String testConfig(){
        OrderMaster orderMaster = orderService.findOne();
        return JSON.toJSONString(orderMaster);
    }


    //读取配置的文件的值
    @Value("${user.env}")
    private String env;

    @GetMapping("env")
    public String getEnv(){
        return env;
    }


}

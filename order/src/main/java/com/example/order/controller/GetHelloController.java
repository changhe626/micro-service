package com.example.order.controller;

import com.example.order.client.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zk
 * @Description:
 * @date 2019-09-18 10:32
 */
@RestController
public class GetHelloController {

    /*@Autowired
    private LoadBalancerClient loadBalancerClient;*/

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("get-hello")
    public void getHello() {
        //第一种方式
        //耦合性太高了,不大可能知道ip地址,以及端口号, 还有涉及到多个服务的问题
        /*RestTemplate template = new RestTemplate();
        String object = template.getForObject("http://localhost:8080/hello", String.class);
        System.out.println(object);*/


        //第二种,利用LoadBalancerClient,利用应用名字获取
        //注册在eureka中的服务名字
        /*ServiceInstance serviceInstance = loadBalancerClient.choose("PRODUCT");
        String format = String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort()) + "/hello";
        RestTemplate template = new RestTemplate();
        String object = template.getForObject(format, String.class);
        System.out.println(object);*/


        //第三种方式,使用RestTemplateConfig ,利用LoadBalanced 使用名字
        String object = restTemplate.getForObject("http://PRODUCT/hello", String.class);
        System.out.println(object);
    }


    @Autowired(required=false)
    private ProductList productList;

    @GetMapping("msg")
    public void getMsg(){
        String message = productList.productMessage();
        System.out.println(message);
    }


}

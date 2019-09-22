package com.example.order.controller;


import com.example.product.client.ProductList;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * 微服务和分布式必须考虑的是:容错   两种处理: 重试和断路器
 */
@RequestMapping("hystrix-demo")
@RestController
//下面的这个方法,没有生效
//@DefaultProperties(defaultFallback = "errorHandle2")   //这个类的所有方法都有熔断处理
public class HystrixController {

    /*@Autowired
    private ProductList productList;*/


    /**
     * 熔断处理外部的错误调用.
     */
    @HystrixCommand(fallbackMethod = "errorHandle")
    @GetMapping("test1")
    public String get() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> entity = restTemplate.postForEntity("http://localhost:8081/product/listForOrder"
                , Arrays.asList("1", "2"), String.class);
        return entity.getBody();
    }


    /**
     * 处理服务内部的错误
     */
    //超时的配置,默认超时时间1s,配置4s
    /*@HystrixCommand(fallbackMethod = "errorHandle2", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
    })*/
    //可以全部写到配置文件里面去.
    /*@HystrixCommand(fallbackMethod = "errorHandle2", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),   //设置熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//时间窗
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")//百分比
    })*/
    //全部写到配置文件里后,只需要很简单的写法了
    @HystrixCommand(fallbackMethod = "errorHandle2")
    @GetMapping("test2")
    public String test2() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity("http://localhost:8081/hello", Void.class);
        //这是成功调用了, 但是等待超时了
        throw new RuntimeException("抛出异常");
    }


    /**
     * 方法名,返回值一致就行了
     */
    public String errorHandle() {
        return "服务器忙,请售后重试";
    }

    /**
     * 方法名,返回值一致就行了
     */
    public String errorHandle2() {
        return "服务器忙,请售后重试2";
    }


}

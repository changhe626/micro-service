package com.example.order.service.impl;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author zk
 * @Description:
 * @date 2019-09-18 12:10
 */

/**
 * 调用哪个服务的接口, product的服务
 */
@FeignClient(name = "product")
public interface ProductList {

    /**
     * 请求的url,下面的方法名可以随意
     * @return
     */
    @GetMapping("/msg")
    String productMessage();


    //@GetMapping("")
   //List<ProductInfo> listForOrder(List<String> strings);

}

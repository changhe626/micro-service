package com.example.order.client;

import com.example.order.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author zk
 * @Description:
 * @date 2019-09-18 12:10
 */

/**
 * 调用哪个服务的接口, product的服务
 */
@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductList {

    /**
     * 请求的url,下面的方法名可以随意
     * @return
     */
    @GetMapping("/hello")
    String productMessage();


    @PostMapping("listForOrder")
    List<ProductInfo> listForOrder(List<String> productList);

}

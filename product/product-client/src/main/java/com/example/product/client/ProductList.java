package com.example.product.client;


import com.example.common.entity.CartDTO;
import com.example.product.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zk
 * @Description:
 * @date 2019-09-18 12:10
 */

/**
 * 调用哪个服务的接口, product的服务
 * 注意了,这里的名字要求是小写的, 如果写成 PRODUCT-SERVICE  会报错, 找不到服务...
 */
@FeignClient(name = "product-service")
public interface ProductList {

    /**
     * 请求的url,下面的方法名可以随意
     * @return
     */
    @GetMapping("/hello")
    String productMessage();


    /**
     * 这里的PostMapping 和 product中的/product/listForOrder 的postmapping 相互对应
     * @param productList
     * @return
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(List<String> productList);


    /**
     * 扣库存
     * @param list
     */
    @PostMapping("/product/descProductStock")
    void descProductStock(@RequestBody List<CartDTO> list);


}

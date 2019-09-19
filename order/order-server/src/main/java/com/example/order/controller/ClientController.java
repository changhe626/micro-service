package com.example.order.controller;


import com.example.common.entity.CartDTO;
import com.example.product.entity.ProductInfo;
import com.example.product.client.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zk
 * @Description:
 * @date 2019-09-18 14:03
 */
@RestController
public class ClientController {

    @Autowired(required = false)
    private ProductList productList;


    @GetMapping("getProductMsg")
    public String getProductMsg(){
        String message = productList.productMessage();
        System.out.println(message);
        return message;
    }


    /**
     * 注意加上前缀了
     * @return
     */
    @GetMapping("/getProductList")
    public String getProductList(){
        List<ProductInfo> productInfoList= productList.listForOrder(Arrays.asList("1","2"));
        System.out.println(productInfoList);
        return "success";
    }


    @GetMapping("/descProductStock")
    public String descProductStock(){
        List<CartDTO> list = new ArrayList<>();
        CartDTO dto = new CartDTO();
        dto.setProductId("1");
        dto.setProductQuantity(1);
        list.add(dto);
        productList.descProductStock(list);
        return "success";
    }



}

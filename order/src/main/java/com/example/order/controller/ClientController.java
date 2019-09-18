package com.example.order.controller;

import com.example.order.service.impl.ProductList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author zk
 * @Description:
 * @date 2019-09-18 14:03
 */
@RestController
public class ClientController {

    @Autowired(required=false)
    private ProductList productList;


    @GetMapping("getProductMsg")
    public String getProductMsg(){
        String message = productList.productMessage();
        System.out.println(message);
        return message;
    }


    @GetMapping("getProductList")
    public String getProductList(){
        //List<ProductInfo> productInfoList= productList.listForOrder(Arrays.asList("1","2"));

        return null;

    }



}

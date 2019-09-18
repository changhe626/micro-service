package com.example.product.service.impl;

import com.example.product.dao.ProductDao;
import com.example.product.dao.ProductDaoTest;
import com.example.product.entity.ProductInfo;
import com.example.product.service.ProductService;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductServiceImplTest  extends ProductDaoTest {

    @Autowired
    private ProductService productService;


    @Test
    public void findUpAll() {
        List<ProductInfo> all = productService.findUpAll();
        System.out.println(all.size());
    }
}
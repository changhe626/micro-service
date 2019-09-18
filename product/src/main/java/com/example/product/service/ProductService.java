package com.example.product.service;

import com.example.product.entity.ProductInfo;

import java.util.List;

public interface ProductService {

    /**
     * 查询所有在架商品
     * @return
     */
    List<ProductInfo> findUpAll();



}

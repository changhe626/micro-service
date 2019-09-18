package com.example.product.service.impl;

import com.example.product.constant.ProductStatusEnum;
import com.example.product.dao.ProductDao;
import com.example.product.entity.ProductInfo;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductInfo> findUpAll() {
        return productDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> listForOrder(List<String> productList) {
        return productDao.findByProductIdIn(productList);
    }
}

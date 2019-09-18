package com.example.product.service.impl;

import com.example.product.dao.ProductCategoryDao;
import com.example.product.entity.ProductCategory;
import com.example.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.ArrayList;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypes) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypes);
    }
}

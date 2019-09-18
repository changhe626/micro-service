package com.example.product.service.impl;

import com.example.product.dao.ProductDaoTest;
import com.example.product.entity.ProductCategory;
import com.example.product.service.CategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class CategoryServiceImplTest  extends ProductDaoTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<ProductCategory> type = categoryService.findByCategoryTypeIn(list);
        System.out.println(type.size());
    }
}
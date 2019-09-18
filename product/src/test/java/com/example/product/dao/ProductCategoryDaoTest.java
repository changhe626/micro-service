package com.example.product.dao;

import com.example.product.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao productCategoryDao;


    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<ProductCategory> type = productCategoryDao.findByCategoryTypeIn(list);
        System.out.println(type.size());

    }
}
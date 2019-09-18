package com.example.product.dao;

import com.example.product.entity.ProductInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> dao = productDao.findByProductStatus(1);
        System.out.println(dao.size());
    }
}
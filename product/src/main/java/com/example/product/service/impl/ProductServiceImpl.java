package com.example.product.service.impl;

import com.example.product.constant.ProductStatusEnum;
import com.example.product.constant.ResultEnum;
import com.example.product.dao.ProductDao;
import com.example.product.dto.CartDTO;
import com.example.product.entity.ProductInfo;
import com.example.product.exception.ProductException;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional
    public void descProductStock(List<CartDTO> list) {
        for (CartDTO cartDTO : list) {
            //先检查产品是否存在
            Optional<ProductInfo> product = productDao.findById(cartDTO.getProductId());
            if (!product.isPresent()) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            //判断库存
            ProductInfo info = product.get();
            int tmp= info.getProductStock()-cartDTO.getProductQuantity();
            if (tmp < 0) {
                throw new ProductException(ResultEnum.STOCK_ERROR);
            }
            info.setProductStock(tmp);
            productDao.save(info);
        }

    }

}

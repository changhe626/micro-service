package com.example.product.service.impl;

import com.example.common.entity.CartDTO;
import com.example.product.entity.ProductInfo;
import com.example.product.constant.ProductStatusEnum;
import com.example.product.constant.ResultEnum;
import com.example.product.dao.ProductDao;
import com.example.product.exception.ProductException;
import com.example.product.service.ProductService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("productService")
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDao productDao;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> listForOrder(List<String> productList) {
        return productDao.findByProductIdIn(productList);
    }

    @Override
    public void descProductStock(List<CartDTO> list) {
        //扣库存
        List<ProductInfo> infos = descProductStock2(list);
        Map<String, Integer> map = infos.stream().collect(Collectors.toMap(ProductInfo::getProductId, ProductInfo::getProductStock));
        //发送消息, 不再发送整个对象过去了,只发送id 和库存
        amqpTemplate.convertAndSend("productInfo", map);
    }


    /**
     * 完成了整个库存的处理完成后再发送消息
     * @param list
     */
    @Transactional
    public List<ProductInfo> descProductStock2(List<CartDTO> list) {
        List<ProductInfo> infos = new ArrayList<>();
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

            infos.add(info);
        }
        return infos;
    }

}

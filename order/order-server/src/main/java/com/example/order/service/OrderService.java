package com.example.order.service;

import com.example.order.dto.OrderDTO;
import com.example.order.entity.OrderMaster;

public interface OrderService {

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    OrderDTO create(OrderDTO orderDTO);

    OrderMaster findOne();

    OrderDTO finishOrder(String orderId);
}

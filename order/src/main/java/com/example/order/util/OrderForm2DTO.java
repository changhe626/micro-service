package com.example.order.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.order.constans.ResultEnum;
import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderForm;
import com.example.order.entity.OrderDetail;
import com.example.order.exception.OrderException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zk
 * @Description:
 * @date 2019-09-18 9:55
 */
public class OrderForm2DTO {

    /**
     * 对象转换
     *
     * @param orderForm
     * @return
     */
    public static OrderDTO convert(OrderForm orderForm) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setOrderId(orderForm.getOpenid());
        List<OrderDetail> list = new ArrayList<>();
        try {
            list = JSON.parseObject(orderForm.getItems(), new TypeReference<List<OrderDetail>>() {
            });
        } catch (Exception e) {
            System.out.println("json转换出错");
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(list);
        return orderDTO;
    }


}

package com.example.order.service.impl;

import com.example.order.constans.OrderStatusEnum;
import com.example.order.constans.PayStatusEnum;
import com.example.order.dao.OrderDetailDao;
import com.example.order.dao.OrderMasterDao;
import com.example.order.dto.OrderDTO;
import com.example.order.entity.OrderDetail;
import com.example.order.entity.OrderMaster;
import com.example.order.service.OrderService;
import com.example.order.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        //1.参数校验
        //2.查询商品信息(调用商品服务)
        //3.计算总价
        // 4.扣除库存(调用商品服务)
        // 5.订单入库
        OrderMaster master = new OrderMaster();
        String key = KeyUtil.getUniqueKey();
        orderDTO.setOrderId(key);
        master.setOrderId(key);
        //这里必须是先复制属性, 再设置其他的属性
        BeanUtils.copyProperties(orderDTO, master);
        master.setOrderAmount(new BigDecimal(4));
        master.setOrderStatus(OrderStatusEnum.NEW.getCode());
        master.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(master);
        return orderDTO;
    }


}

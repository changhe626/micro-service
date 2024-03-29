package com.example.order.service.impl;

import com.example.common.entity.CartDTO;
import com.example.order.constans.ResultEnum;
import com.example.order.dao.OrderDetailDao;
import com.example.order.dao.OrderMasterDao;
import com.example.order.constans.OrderStatusEnum;
import com.example.order.constans.PayStatusEnum;
import com.example.order.dto.OrderDTO;
import com.example.order.entity.OrderDetail;
import com.example.order.entity.OrderMaster;
import com.example.order.exception.OrderException;
import com.example.order.service.OrderService;
import com.example.common.util.KeyUtil;
import com.example.product.client.ProductList;
import com.example.product.entity.ProductInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired(required = false)
    private ProductList productList;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.getUniqueKey();
        //1.参数校验
        //2.查询商品信息(调用商品服务)
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        List<String> list = orderDetailList.stream().map(OrderDetail::getProductId).collect(Collectors.toList());
        List<ProductInfo> productInfos = productList.listForOrder(list);

        //读redis
        //减库存并且重新设置值到redis
        //订单入库异常,需要手动回滚redis


        //3.计算总价
        BigDecimal sum = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail detail : orderDetailList) {
            for (ProductInfo info : productInfos) {
                if (info.getProductId().equals(detail.getProductId())) {
                    BigDecimal decimal = info.getProductPrice().multiply(new BigDecimal(detail.getProductQuantity()));
                    sum = sum.add(decimal);
                    BeanUtils.copyProperties(info, detail);
                    detail.setOrderId(orderId);
                    detail.setDetailId(KeyUtil.getUniqueKey());
                    //订单详情入库
                    orderDetailDao.save(detail);
                }
            }
        }

        // 4.扣除库存(调用商品服务)
        List<CartDTO> dtos = orderDetailList.stream().
                map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());

        productList.descProductStock(dtos);
        // 5.订单入库
        OrderMaster master = new OrderMaster();

        orderDTO.setOrderId(orderId);
        master.setOrderId(orderId);
        //这里必须是先复制属性, 再设置其他的属性
        BeanUtils.copyProperties(orderDTO, master);
        master.setOrderAmount(sum);
        master.setOrderStatus(OrderStatusEnum.NEW.getCode());
        master.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(master);
        return orderDTO;
    }

    @Override
    public OrderMaster findOne() {
        return orderMasterDao.findById("1568857515788780824").get();
    }


    /**
     * 完结订单 ,只能卖家操作
     */
    @Override
    @Transactional
    public OrderDTO finishOrder(String orderId) {
        //1.查询订单是否存在
        Optional<OrderMaster> orderMaster = orderMasterDao.findById(orderId);
        if (!orderMaster.isPresent()) {
            throw new OrderException(ResultEnum.ORDER_NOT_EXISTS);
        }
        //2.判断订单状态
        OrderMaster master = orderMaster.get();
        if (OrderStatusEnum.NEW.getCode() != master.getOrderStatus()) {
            throw new OrderException(ResultEnum.ORDER_ERROR);
        }
        //3.修改订单状态为完结.
        master.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterDao.save(master);

        //4.查询订单详情,返回
        List<OrderDetail> orderDetails = orderDetailDao.findByOrderId(orderId);
        if (orderDetails == null) {
            //订单详情不存在.
            throw new OrderException(ResultEnum.ORDER_DETAIL_NOT_EXISTS);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(master,orderDTO);
        orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }


}

package com.example.order.controller;

import com.example.common.util.ResultVoUtil;
import com.example.common.vo.ResultVo;
import com.example.order.constans.ResultEnum;
import com.example.order.dto.OrderDTO;
import com.example.order.dto.OrderForm;

import com.example.order.entity.OrderDetail;
import com.example.order.exception.OrderException;
import com.example.order.service.OrderService;
import com.example.order.util.OrderForm2DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("com/example/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 1.参数校验
     * 2.查询商品信息(调用商品服务)
     * 3.计算总价
     * 4.扣除库存(调用商品服务)
     * 5.订单入库
     */
    @PostMapping("create")
    public ResultVo create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("参数错误");
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        //orderForm -> orderDTO
        OrderDTO orderDTO = OrderForm2DTO.convert(orderForm);
        //判断购物车是否为空
        List<OrderDetail> list = orderDTO.getOrderDetailList();
        if (list == null || list.size() == 0) {
            throw new OrderException(ResultEnum.CAET_EMPTY);
        }
        OrderDTO dto = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId",dto.getOrderId());
        ResultVo resultVo = ResultVoUtil.success(map);
        return resultVo;
    }


}

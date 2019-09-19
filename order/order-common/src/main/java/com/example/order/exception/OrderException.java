package com.example.order.exception;

import com.example.order.constans.ResultEnum;

/**
 * @author zk
 * @Description:订单的异常
 * @date 2019-09-18 9:46
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}

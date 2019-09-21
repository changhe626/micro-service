package com.example.order.constans;

/**
 * @author zk
 * @Description:
 * @date 2019-09-18 9:50
 */
public enum ResultEnum {

    PARAM_ERROR(1,"参数错误"),
    CAET_EMPTY(2,"购物车为空"),
    ORDER_NOT_EXISTS(3,"订单不存在"),
    ORDER_ERROR(4,"订单状态错误"),
    ORDER_DETAIL_NOT_EXISTS(5,"订单详细错误"),
    ;

    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}


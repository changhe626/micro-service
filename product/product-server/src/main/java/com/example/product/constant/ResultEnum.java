package com.example.product.constant;

/**
 * @author zk
 * @Description:
 * @date 2019-09-19 11:13
 */
public enum ResultEnum {

    PRODUCT_NOT_EXISTS(1,"商品不存在"),
    STOCK_ERROR(2, "库存错误");


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

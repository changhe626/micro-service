package com.example.product.exception;

import com.example.product.constant.ResultEnum;

/**
 * @author zk
 * @Description:
 * @date 2019-09-19 11:11
 */
public class ProductException extends  RuntimeException{

    private int code;
    private String meg;


    public ProductException(int code, String meg) {
        super(meg);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}

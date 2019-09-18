package com.example.order.dto;

public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo vo = new ResultVo();
        vo.setCode(0);
        vo.setMsg("success");
        vo.setData(object);
        return vo;
    }



}

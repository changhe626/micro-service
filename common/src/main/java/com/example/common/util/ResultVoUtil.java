package com.example.common.util;


import com.example.common.vo.ResultVo;

/**
 * 结果封装的工具类
 */
public class ResultVoUtil {

    /**
     * 成功
     * @param object
     * @return
     */
    public static ResultVo success(Object object){
        ResultVo vo = new ResultVo<>();
        vo.setCode(0);
        vo.setData(object);
        vo.setMsg("success");
        return vo;
    }


    public static ResultVo success(){
        ResultVo vo = new ResultVo<>();
        vo.setCode(0);
        vo.setMsg("success");
        return vo;
    }


    /**
     * 失败
     * @param object
     * @return
     */
    public static ResultVo fail(Object object){
        ResultVo vo = new ResultVo<>();
        vo.setCode(-1);
        vo.setData(object);
        vo.setMsg("fail");
        return vo;
    }


}

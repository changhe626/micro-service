package com.example.order.controller;

import com.alibaba.fastjson.JSON;
import com.example.order.dto.GirlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zk
 * @Description:
 * @date 2019-09-20 13:52
 */
@RestController
public class GirlController {

    /**
     * 因为在 GirlConfig 上已经添加了 RefreshScope 注解,
     * 所以GirlController 上就不要再加了
     */
    @Autowired
    private GirlConfig girlConfig;

    @GetMapping("girl")
    public String getGirlConfig(){
        return JSON.toJSONString(girlConfig);
    }


}

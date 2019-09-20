package com.example.order.message;

import com.example.order.constans.MessageConstant;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author zk
 * @Description:
 * @date 2019-09-20 16:19
 */
public interface StreamClient {


    /**
     * 这里的input 和 output 必须是不同的字符串, 不然是同名的bean错误
     */
    @Input(MessageConstant.INPUT)
    SubscribableChannel input();


    @Output(MessageConstant.OUTPUT)
    MessageChannel output();


}

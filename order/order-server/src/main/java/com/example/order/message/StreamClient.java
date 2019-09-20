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


    @Input(MessageConstant.MSG1)
    SubscribableChannel input();


    @Output(MessageConstant.MSG1)
    MessageChannel output();


    @Input(MessageConstant.MSG2)
    SubscribableChannel input2();


    @Output(MessageConstant.MSG2)
    MessageChannel output2();


}

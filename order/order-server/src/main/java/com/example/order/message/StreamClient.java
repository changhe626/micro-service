package com.example.order.message;

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
     * 常量的替换
     */
    String INPUT="myMessage";

    String INPUT2="myMessage2";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();


    @Output(StreamClient.INPUT)
    MessageChannel output();


    @Input(StreamClient.INPUT2)
    SubscribableChannel input2();


    @Output(StreamClient.INPUT2)
    MessageChannel output2();


}

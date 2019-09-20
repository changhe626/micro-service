package com.example.order.message;

import com.example.order.constans.MessageConstant;
import com.example.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author zk
 * @Description: 接受消息
 * @date 2019-09-20 16:21
 */
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

    /**
     * 接受字符串
     */
    /*@StreamListener(MessageConstant.INPUT)
    public void process(Object msg) {
        System.out.println(msg);
    }*/


    /**
     * 接受orderDTO对象
     */
    @StreamListener(MessageConstant.INPUT)
    @SendTo(MessageConstant.OUTPUT)//处理完消息,再回发送个消息
    public Object process(OrderDTO msg) {
        System.out.println(msg);
        //发送消息
        return "这是消息";
    }


    /**
     * 接受上面的回传的消息
     */
    @StreamListener(MessageConstant.OUTPUT)
    public void process2(String msg) {
        System.out.println(msg);
    }


}

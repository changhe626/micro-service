package com.example.order.controller;

import com.example.order.constans.MessageConstant;
import com.example.order.dto.OrderDTO;
import com.example.order.message.StreamClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author zk
 * @Description:
 * @date 2019-09-20 16:24
 */
@RestController
public class SendMessageController {


    /**
     * 往rabbitmq发送消息
     */
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("send")
    public String sendMessage() {
        //可能需要实现创建一个myQueue的队列
        amqpTemplate.convertAndSend(MessageConstant.QUEUE_NAME, "this is the first message");
        amqpTemplate.convertAndSend("myOrder", "computer", "this is the first computer  message");
        amqpTemplate.convertAndSend("myOrder", "fruit", "this is the first message");
        return "success";
    }


    @Autowired(required = false)
    private StreamClient streamClient;

    /**
     * 发送字符串对象
     *
     * @return
     */
    /*@GetMapping("send2")
    public String send2() {
        String msg = "time  is today, haha ";
        System.out.println("发送的消息是:" + msg);
        streamClient.output().send(MessageBuilder.withPayload(msg).build());
        return "success";
    }*/


    /**
     * 发送java bean对象
     * 这个send3 不能和send2 使用的同一个变量.但是不能共存, 需要注释掉, 不然报错的...
     *
     * @return
     */
    @GetMapping("send3")
    public String send3(){
        OrderDTO dto = new OrderDTO();
        dto.setBuyerAddress("address");
        dto.setBuyerName("xiexin");
        streamClient.output().send(MessageBuilder.withPayload(dto).build());
        return "success";
    }


}

package com.example.order.controller;

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
    public String sendMessage(){
        //可能需要实现创建一个myQueue的队列
        amqpTemplate.convertAndSend("myQueue","this is the first message");
        amqpTemplate.convertAndSend("myOrder","computer","this is the first computer  message");
        amqpTemplate.convertAndSend("myOrder","fruit","this is the first message");
        return "success";
    }


    @Autowired(required = false)
    private StreamClient streamClient;

    /**
     * 发送字符串对象
     * @return
     */
    @GetMapping("send2")
    public String send2(){
        String msg= "now is"+ LocalDateTime.now();
        streamClient.output().send(MessageBuilder.withPayload(msg).build());
        return "success";
    }


    /**
     * 发送java bean对象
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

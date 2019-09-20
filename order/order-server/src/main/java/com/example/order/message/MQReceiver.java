package com.example.order.message;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zk
 * @Description:mq 接收消息
 * @date 2019-09-20 15:29
 */
@Component
public class MQReceiver {

    //1.手动创建队列
    //@RabbitListener(queues="myQueue")

    //2.自动创建队列
    //@RabbitListener(queuesToDeclare = @Queue("myQueue"))

    //3.自动创建 exchange 和 queue绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue")
            ,exchange = @Exchange("myExchange"))
    )
    public void process(String message){
        System.out.println("接收到的消息是:"+message);
    }

    /**
     * 数码消息接受商
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "computer",
            value = @Queue("computerOrder"))
    )
    public void computerProcess(String message){
        System.out.println("computer接收到的消息是:"+message);
    }



    /**
     * 水果消息接受商
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myOrder"),
            key = "fruit",
            value = @Queue("fruitOrder"))
    )
    public void fruitProcess(String message){
        System.out.println("fruit接收到的消息是:"+message);
    }



}

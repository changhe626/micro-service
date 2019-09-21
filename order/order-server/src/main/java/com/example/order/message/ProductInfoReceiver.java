package com.example.order.message;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.product.entity.ProductInfo;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class ProductInfoReceiver {

    private final static String HASH_KEY="product_stock";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 传递整个对象的方法
     * @param message
     */
    //@RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process0(String message) {
        //注意反序列化的时候,两个对象的前缀要求是一样的(包名)
        //ProductInfo info = JSON.parseObject(message, ProductInfo.class);
        List<ProductInfo> infos = JSON.parseObject(message, new TypeReference<List<ProductInfo>>() {
        });
        System.out.println("接受到的消息是:" + infos);

        //存储到redis中去.
        //stringRedisTemplate.opsForValue()
               // .set("product_stock_" + info.getProductId(), String.valueOf(info.getProductStock()));

        /**
         * 单值的key -value 可以储存, 现在多个的时候, 最好使用hash 的方式进行存储
         * 这种单值的存储方式不好...
         * product_stock        1       5
         *      key           field   value
         */
        for (ProductInfo info : infos) {
            stringRedisTemplate.opsForHash().put(HASH_KEY,info.getProductId(),info.getProductStock());
        }

    }


    /**
     * 只传递id和剩余库存的方法
     * 这里的参数的接收对象一定和发送的一致,不然rabbitmq会报类型转换错误.
     * json转换后,就是json的数组或者json字符串了.上面的那个方法是个反例
     * @param message
     */
    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(Map<String,Integer> message) {
        //注意反序列化的时候,两个对象的前缀要求是一样的(包名)
        System.out.println("接受到的消息是:" + message);

        //存储到redis中去.
        //stringRedisTemplate.opsForValue()
        // .set("product_stock_" + info.getProductId(), String.valueOf(info.getProductStock()));

        /**
         * 单值的key -value 可以储存, 现在多个的时候, 最好使用hash 的方式进行存储
         * 这种单值的存储方式不好...
         * product_stock        1       5
         *      key           field   value
         */
        for (Map.Entry<String, Integer> entry : message.entrySet()) {
            stringRedisTemplate.opsForHash()
                    .put(HASH_KEY,entry.getKey(),String.valueOf(entry.getValue()));
        }

    }



}

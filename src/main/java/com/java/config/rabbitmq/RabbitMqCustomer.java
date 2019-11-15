package com.java.config.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 消费者
 *
 * @author yupan@yijiupi.cn
 * @date 2018/12/21 16:46
 */
@Service
public class RabbitMqCustomer {

    @RabbitListener(queues = "mq.fanout.A")
    public void receive(Message message) throws Exception {
        System.out.println("接收到消息：" + new String(message.getBody(), "UTF-8"));
    }
}

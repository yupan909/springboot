package com.java.config.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发送者
 *
 * @author yupan@yijiupi.cn
 * @date 2018/12/21 16:46
 */
@Service
public class RabbitMqProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     *
     * @param obj
     */
    public void send(Object obj) {
        rabbitTemplate.convertAndSend("ex.fanout", "", obj);
    }
}

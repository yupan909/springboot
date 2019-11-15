package com.java.config.rabbitmq;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitMQ 转化器配置
 *
 * @author yupan@yijiupi.cn
 * @date 2018/12/21 16:43
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 使用json格式序列化
     *
     * @return
     */
    @Bean
    public MessageConverter myMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

package com.sys.biller.service;

import com.sys.biller.config.RabbitMQConfig;
import com.sys.biller.util.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillerService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TopicExchange topicExchange;


    public void send() {
        rabbitTemplate.convertAndSend(topicExchange.getName(), ""+ Message.SUCCESS.getCode(), Message.SUCCESS.getDescription());
        System.out.println(" [x] Sent '" + Message.SUCCESS.getDescription() + "'");
    }


}

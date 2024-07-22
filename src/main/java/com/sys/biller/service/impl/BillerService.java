package com.sys.biller.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sys.biller.config.RabbitMQConfig;
import com.sys.biller.dto.BillerRequestDto;
import com.sys.biller.service.IBillerService;
import com.sys.biller.util.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillerService implements IBillerService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TopicExchange topicExchange;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void send() {
        rabbitTemplate.convertAndSend(topicExchange.getName(), ""+ Message.SUCCESS.getCode(), Message.SUCCESS.getDescription());
        System.out.println(" [x] Sent '" + Message.SUCCESS.getDescription() + "'");
    }

    @RabbitListener(queues = RabbitMQConfig.VENDOR_QUEUE_NAME)
    public void receive(String message) {
        System.out.println(message);
        try {
            BillerRequestDto billerRequestDto = objectMapper.readValue(message, BillerRequestDto.class);
            System.out.println(billerRequestDto.getMobileNumber());
            // Process the received object
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package com.sys.biller.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sys.biller.config.RabbitMQConfig;
import com.sys.biller.dto.BillerRequestDto;
import com.sys.biller.service.IBillerService;
import com.sys.biller.util.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BillerService implements IBillerService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    TopicExchange topicExchange;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void send() throws JsonProcessingException {

        String json = objectMapper.writeValueAsString("success");

        try{

            rabbitTemplate.convertAndSend(topicExchange.getName(), ""+
                                        Message.SUCCESS.getCode(),
                                        objectMapper.writeValueAsString(Message.SUCCESS.getDescription()));

            log.info(" [x] Sent '" + Message.SUCCESS.getDescription() + "'");

        }catch (AmqpException e){
            log.error(e.getMessage());
        }

    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public String receive(String message) {
        log.info(message);
        System.out.println(message);
        try {
            BillerRequestDto billerRequestDto = objectMapper.readValue(message, BillerRequestDto.class);
            /**
             * do business concern
             */

            return "success billing process.";
            // Process the received object
        } catch (Exception e) {
            e.printStackTrace();
            return "fail billing process.";
        }
    }


}

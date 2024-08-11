package com.sys.biller.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sys.biller.config.RabbitMQConfig;
import com.sys.biller.dto.PromotionMessageDto;
import com.sys.biller.service.IPromotionInformationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class PromotionInformationService implements IPromotionInformationService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void sendPromotionMsg(PromotionMessageDto dto) {


        try {

            String jsonDto = objectMapper.writeValueAsString(dto);
            rabbitTemplate.convertAndSend(RabbitMQConfig.VENDOR_PROMO_MSG, jsonDto);
           // log.info("Success Message Sent.");
        }
       catch (JsonProcessingException e) {
           e.printStackTrace();
       }

    }
}

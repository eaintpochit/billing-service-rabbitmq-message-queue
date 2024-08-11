package com.sys.biller.controller;

import com.sys.biller.dto.PromotionMessageDto;
import com.sys.biller.service.IPromotionInformationService;
import com.sys.biller.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biller")
public class PromotionInformationController {

    @Autowired
    IPromotionInformationService promotionInformationService;

    @PostMapping("/promotion/message")
    public ResponseEntity<?> sendPromotionInformation(@RequestBody PromotionMessageDto dto){

        try{
            promotionInformationService.sendPromotionMsg(dto);
            return new ResponseEntity<>(Message.SUCCESS.getDescription() ,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Message.FAIL.getDescription(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

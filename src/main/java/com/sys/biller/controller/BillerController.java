package com.sys.biller.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sys.biller.service.IBillerService;
import com.sys.biller.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/biller")
public class BillerController {

    @Autowired
    IBillerService billedService;

    @PostMapping("/publish")
    public ResponseEntity<?> publishMessage() throws JsonProcessingException {
        try{
            billedService.send();
            return new ResponseEntity<>(Message.SUCCESS.getDescription(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Message.FAIL.getDescription(),HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}

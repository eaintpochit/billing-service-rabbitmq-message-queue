package com.sys.biller.controller;

import com.sys.biller.service.BillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/biller")
public class BillerController {

    @Autowired
    BillerService billerService;

    @PostMapping("/publish")
    public ResponseEntity<?> publishMessage() {
        billerService.send();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

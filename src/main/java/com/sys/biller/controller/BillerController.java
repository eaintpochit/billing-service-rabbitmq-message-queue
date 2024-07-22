package com.sys.biller.controller;

import com.sys.biller.service.IBillerService;
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
    public ResponseEntity<?> publishMessage() {
        billedService.send();
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}

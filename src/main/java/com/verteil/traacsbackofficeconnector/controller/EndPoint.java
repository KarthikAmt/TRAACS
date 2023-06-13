package com.verteil.traacsbackofficeconnector.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndPoint {

    @GetMapping("message")
    public String getMessage(){
            return "ab";
    }
}

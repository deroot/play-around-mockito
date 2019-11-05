package com.example.playaroundmockito.controller;

import com.example.playaroundmockito.model.GreetingMessage;
import com.example.playaroundmockito.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("greeting")
    public ResponseEntity hello() {
        GreetingMessage greetingMessage = greetingService.sayHello();
        return ResponseEntity.ok(greetingMessage);
    }
}

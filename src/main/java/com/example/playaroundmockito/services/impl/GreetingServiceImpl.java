package com.example.playaroundmockito.services.impl;

import com.example.playaroundmockito.model.GreetingMessage;
import com.example.playaroundmockito.services.GreetingService;
import com.example.playaroundmockito.services.TimeStampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private TimeStampService timeStampService;

    @Override
    public GreetingMessage sayHello() {
        Date now = timeStampService.generateTimestamp();
        return GreetingMessage.builder()
                .message("Hello Mar")
                .timestamp(now)
                .build();
    }

    @Override
    public GreetingMessage sayHelloWithName(String name) {
        Date now = timeStampService.generateTimestamp();
        timeStampService.generateFor(name);

        String message = String.format("Hello %s", name);

        return GreetingMessage.builder()
                .message(message)
                .timestamp(now)
                .build();
    }
}

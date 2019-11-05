package com.example.playaroundmockito.services.impl;

import com.example.playaroundmockito.services.TimeStampService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TimeStampServiceImpl implements TimeStampService {
    @Override
    public Date generateTimestamp() {
        return new Date();
    }
}

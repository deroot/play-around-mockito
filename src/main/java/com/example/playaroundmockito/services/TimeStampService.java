package com.example.playaroundmockito.services;

import java.util.Date;

public interface TimeStampService {
    Date generateTimestamp();

    void generateFor(String name);
}

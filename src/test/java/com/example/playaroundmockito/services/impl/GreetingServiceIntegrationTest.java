package com.example.playaroundmockito.services.impl;

import com.example.playaroundmockito.model.GreetingMessage;
import com.example.playaroundmockito.services.TimeStampService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Note: in Junit 4 using ->
// @RunWith(SpringRunner.class)
// @SpringBootTest
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingServiceIntegrationTest {

    @Autowired
    private GreetingServiceImpl greetingService;

    @MockBean
    private TimeStampService timeStampService;

    @Test
    void sayHello() throws Exception {
        Date mockDate = DateUtils.parseDate("2019-01-01", "yyyy-MM-dd");
        when(timeStampService.generateTimestamp()).thenReturn(mockDate);

        GreetingMessage greetingMessage = greetingService.sayHello();

        verify(timeStampService).generateTimestamp();
        assertEquals(mockDate, greetingMessage.getTimestamp());

    }
}
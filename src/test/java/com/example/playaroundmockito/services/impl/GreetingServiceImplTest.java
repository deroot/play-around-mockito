package com.example.playaroundmockito.services.impl;

import com.example.playaroundmockito.model.GreetingMessage;
import com.example.playaroundmockito.services.TimeStampService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Note: in Junit 4 using -> @RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("Demo Spring Boot unit test with JUnit 5 + Mockito")
public class GreetingServiceImplTest {

    @Spy
    @InjectMocks
    private GreetingServiceImpl greetingService;

    @Mock
    private TimeStampService timeStampService;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    void sayHello() throws Exception {
        Date mockDate = DateUtils.parseDate("2019-01-01", "yyyy-MM-dd");
        when(timeStampService.generateTimestamp()).thenReturn(mockDate);

        GreetingMessage greetingMessage = greetingService.sayHello();

        verify(timeStampService).generateTimestamp();
        assertEquals(mockDate, greetingMessage.getTimestamp());

    }

    @Test
    void sayHelloWithParam() throws Exception {

        // given
        Date mockDate = DateUtils.parseDate("2019-01-01", "yyyy-MM-dd");
        when(timeStampService.generateTimestamp()).thenReturn(mockDate);

        // when
        String name = "Scott";
        GreetingMessage greetingMessage = greetingService.sayHelloWithName(name);

        // then
        verify(greetingService).sayHelloWithName("Scott"); // @Spy

        verify(timeStampService).generateFor(stringArgumentCaptor.capture()); // @Captor
        assertEquals("Scott", stringArgumentCaptor.getValue());
    }
}
package com.example.playaroundmockito.controller;

import com.example.playaroundmockito.model.GreetingMessage;
import com.example.playaroundmockito.services.TimeStampService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// Note: in Junit 4 using ->
// @RunWith(SpringRunner.class)
// @SpringBootTest
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private TimeStampService timeStampService;

    @Test
    @DisplayName("Integration test of the BarService")
    void greetingTest() throws Exception {

        // given
        Date mockDate = DateUtils.parseDate("2019-01-01", "yyyy-MM-dd");
        when(timeStampService.generateTimestamp()).thenReturn(mockDate);

        // when
        String url = String.format("http://localhost:%s/greeting",port);
        ResponseEntity<GreetingMessage> response = restTemplate.getForEntity(url, GreetingMessage.class);

        // then
        assertEquals("Hello Mar", response.getBody().getMessage());
        assertEquals(mockDate, response.getBody().getTimestamp());
    }
}
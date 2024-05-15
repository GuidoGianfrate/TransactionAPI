package com.guidogianfrate.demo.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionIntegrationErrorTests {

    private String BASE_PATH = null;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        BASE_PATH = "http://localhost:" + port + "/transactions";
    }

    @Test
    void getSumOfNotExistentTransactionShouldThrowException() {
        String urlGetSum10 = BASE_PATH + "/sum/10";
        ResponseEntity<String> sumId10 = restTemplate.getForEntity(urlGetSum10, String.class);
        assertThat(sumId10.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}

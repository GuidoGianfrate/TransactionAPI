package com.guidogianfrate.demo.integration;


import com.guidogianfrate.demo.dto.NewTransactionDTO;
import com.guidogianfrate.demo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionIntegrationChallengePDFTests {

    private String BASE_PATH = null;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    public void setup(){
        BASE_PATH = "http://localhost:" + port + "/transactions";
    }

    @Test
    void integrationTestChallengeMendelExampleOnPDF() {
        // PUT /transactions/10 { "amount": 5000, "type": "cars" } => { "status": "ok" }
        createTransaction(10L, 5000.0, "cars", Optional.empty());

        // PUT /transactions/11 { "amount": 10000, "type": "shopping", "parent_id": 10 }
        createTransaction(11L, 10000.0, "shopping", Optional.of(10L));

        // PUT /transactions/12 { "amount": 5000, "type": "shopping", "parent_id": 11 }
        createTransaction(12L, 5000.0, "shopping", Optional.of(11L));

        // GET /transactions/types/cars => [10]
        String urlByType = BASE_PATH + "/types/cars";
        ResponseEntity<List<Long>> listOfTransactionIds = restTemplate.exchange(urlByType, HttpMethod.GET, new HttpEntity<>(null),new ParameterizedTypeReference<List<Long>>() {});
        assertThat(listOfTransactionIds.getBody(), contains(10L));

        // GET /transactions/sum/10 => {"sum":20000}
        String urlGetSum10 = BASE_PATH + "/sum/10";
        ResponseEntity<Double> sumId10 = restTemplate.getForEntity(urlGetSum10, Double.class);
        assertThat(sumId10.getBody(), is(20000.0));

        // GET /transactions/sum/11 => {"sum":15000}
        String urlGetSum11 = BASE_PATH + "/sum/11";
        ResponseEntity<Double> sumId11 = restTemplate.getForEntity(urlGetSum11, Double.class);
        assertThat(sumId11.getBody(), is(15000.0));
    }

    private void createTransaction(Long id, double amount, String type, Optional<Long> parentId){
        String url = BASE_PATH + "/" + id;

        NewTransactionDTO newTransactionDTO = NewTransactionDTO.builder()
                .amount(amount)
                .type(type)
                .parentId(parentId)
                .build();

        HttpEntity httpEntity = new HttpEntity(newTransactionDTO);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, Void.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(transactionRepository.findById(id), notNullValue());
    }
}

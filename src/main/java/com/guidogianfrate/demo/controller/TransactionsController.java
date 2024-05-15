package com.guidogianfrate.demo.controller;

import com.guidogianfrate.demo.dto.NewTransactionDTO;
import com.guidogianfrate.demo.exception.TransactionNotFoundException;
import com.guidogianfrate.demo.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    /**
     * \
     * @param transactionId Id of the new transaction
     * @param newTransactionDTO request body with necessary data to create the new transaction
     * @return the created transaction
     */
    @PutMapping("/{transactionId}")
    public ResponseEntity<Void> addNewTransaction(@PathVariable Long transactionId, @RequestBody NewTransactionDTO newTransactionDTO) {
        transactionsService.createNewTransaction(transactionId, newTransactionDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     *
     * @param type
     * @return A list of transaction Id, with the specified type
     */
    @GetMapping("/types/{type}")
    public ResponseEntity<List<Long>> findAllTransactionsIdByType(@PathVariable String type){
        return new ResponseEntity<>(transactionsService.getTransactionsIdsByType(type), HttpStatus.OK);
    }

    /**
     *
     * @param transactionId
     * @return the summary of the amount specified on the transaction specified,
     * and each amount that has this transaction as a parent
     */
    @GetMapping("/sum/{transactionId}")
    public ResponseEntity<Double> getTotalAmountTransitive(@PathVariable Long transactionId) throws TransactionNotFoundException {
        return new ResponseEntity<>(transactionsService.getTotalAmountTransitive(transactionId), HttpStatus.OK);
    }
}

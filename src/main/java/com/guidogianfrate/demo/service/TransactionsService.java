package com.guidogianfrate.demo.service;

import com.guidogianfrate.demo.dto.NewTransactionDTO;

import java.util.List;

public interface TransactionsService {

   void createNewTransaction(Long id, NewTransactionDTO newTransactionDTO);

   List<Long> getTransactionsIdsByType(String type);




}

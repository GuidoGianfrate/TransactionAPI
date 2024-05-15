package com.guidogianfrate.demo.service;

import com.guidogianfrate.demo.dto.NewTransactionDTO;
import com.guidogianfrate.demo.exception.TransactionNotFoundException;

import java.util.List;

public interface TransactionsService {

   void createNewTransaction(Long id, NewTransactionDTO newTransactionDTO);

   List<Long> getTransactionsIdsByType(String type);

   Double getTotalAmountTransitive(Long id) throws TransactionNotFoundException;




}

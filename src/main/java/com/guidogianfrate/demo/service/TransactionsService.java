package com.guidogianfrate.demo.service;

import com.guidogianfrate.demo.dto.NewTransactionDTO;
import com.guidogianfrate.demo.dto.SumResponse;
import com.guidogianfrate.demo.exception.TransactionNotFoundException;
import com.guidogianfrate.demo.model.TransactionModel;

import java.util.List;

public interface TransactionsService {

   void createNewTransaction(Long id, TransactionModel transactionModel);

   List<Long> getTransactionsIdsByType(String type);

   Double getTotalAmountTransitive(Long id) throws TransactionNotFoundException;




}

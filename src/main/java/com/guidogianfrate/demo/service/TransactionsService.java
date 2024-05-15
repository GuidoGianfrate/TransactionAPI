package com.guidogianfrate.demo.service;

import com.guidogianfrate.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {

    @Autowired
    private TransactionRepository transactionRepository;

}

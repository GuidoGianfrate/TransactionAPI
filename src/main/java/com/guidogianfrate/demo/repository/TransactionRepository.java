package com.guidogianfrate.demo.repository;

import com.guidogianfrate.demo.model.TransactionModel;

import java.util.List;

public interface TransactionRepository {
    TransactionModel findById(Long id);
    void save(TransactionModel transactionModel);
    List<TransactionModel> findByType();

    List<TransactionModel> findByParentId();
}

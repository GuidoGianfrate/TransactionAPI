package com.guidogianfrate.demo.repository;

import com.guidogianfrate.demo.model.TransactionModel;

import java.util.List;

public interface TransactionRepository {
    TransactionModel findById(Long id);
    void save(Long id, TransactionModel transactionModel);
    List<TransactionModel> findByType(String type);

    List<TransactionModel> findByParentId();
}

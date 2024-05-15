package com.guidogianfrate.demo.repository;

import com.guidogianfrate.demo.model.TransactionModel;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
    Optional<TransactionModel> findById(Long id);
    void save(Long id, TransactionModel transactionModel);
    List<TransactionModel> findByType(String type);

    List<TransactionModel> findByParentId(Long id);
}

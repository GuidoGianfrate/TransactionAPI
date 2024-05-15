package com.guidogianfrate.demo.repository;

import com.guidogianfrate.demo.model.TransactionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DefaultTransactionRepository implements TransactionRepository{

    @Override
    public TransactionModel findById(Long id) {
        return null;
    }

    @Override
    public void save(TransactionModel transactionModel) {

    }

    @Override
    public List<TransactionModel> findByType() {
        return null;
    }

    @Override
    public List<TransactionModel> findByParentId() {
        return null;
    }
}

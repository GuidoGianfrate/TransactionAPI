package com.guidogianfrate.demo.repository;

import com.guidogianfrate.demo.model.TransactionModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class DefaultTransactionRepository implements TransactionRepository{

    private HashMap<Long, TransactionModel> transactions = new HashMap<>();

    @Override
    public TransactionModel findById(Long id) {
        return transactions.get(id);
    }

    @Override
    public void save(Long id, TransactionModel transactionModel) {
        transactions.put(id, transactionModel);
    }

    @Override
    public List<TransactionModel> findByType(String type) {
        List<TransactionModel> transactionModels = new ArrayList<>();
        for(TransactionModel transactionModel: transactions.values()){
            if(transactionModel.getType().equals(type)){
                transactionModels.add(transactionModel);
            }
        }
        return transactionModels;
    }

    @Override
    public List<TransactionModel> findByParentId() {
        return null;
    }
}

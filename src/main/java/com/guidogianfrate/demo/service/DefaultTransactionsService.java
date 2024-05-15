package com.guidogianfrate.demo.service;

import com.guidogianfrate.demo.dto.NewTransactionDTO;
import com.guidogianfrate.demo.model.TransactionModel;
import com.guidogianfrate.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.guidogianfrate.demo.mapper.TransactionMapper.mapToModel;

@Service
public class DefaultTransactionsService implements TransactionsService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void createNewTransaction(Long id, NewTransactionDTO newTransactionDTO) {
        TransactionModel transactionModel = mapToModel(newTransactionDTO);
        transactionModel.setId(id);
        transactionRepository.save(id, transactionModel);
    }

    @Override
    public List<Long> getTransactionsIdsByType(String type) {
        List<TransactionModel> transactionByType = transactionRepository.findByType(type);
        if(transactionByType.isEmpty()) return Collections.EMPTY_LIST;
        return transactionByType.stream().map(TransactionModel::getId).collect(Collectors.toList());
    }
}

package com.guidogianfrate.demo.service;

import com.guidogianfrate.demo.dto.NewTransactionDTO;
import com.guidogianfrate.demo.exception.TransactionNotFoundException;
import com.guidogianfrate.demo.model.TransactionModel;
import com.guidogianfrate.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    @Override
    public Double getTotalAmountTransitive(Long id) throws TransactionNotFoundException {
        TransactionModel transaction = findById(id);
        return calculateTotalAmount(transaction);
    }
    private double calculateTotalAmount(TransactionModel transaction) {
        double sum = transaction.getAmount();
        List<TransactionModel> children = transactionRepository.findByParentId(transaction.getId());
        for (TransactionModel child : children) {
            sum = sum + calculateTotalAmount(child);
        }
        return sum;
    }

    private TransactionModel findById(Long id) throws TransactionNotFoundException {
        Optional<TransactionModel> transactionModelOptional = transactionRepository.findById(id);
        if(transactionModelOptional.isEmpty())throw new TransactionNotFoundException("The Transaction with the specified id does not exist");
        return transactionModelOptional.get();
    }
}

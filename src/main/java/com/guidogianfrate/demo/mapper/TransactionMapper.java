package com.guidogianfrate.demo.mapper;

import com.guidogianfrate.demo.dto.NewTransactionDTO;
import com.guidogianfrate.demo.model.TransactionModel;

public class TransactionMapper {
    
    public static TransactionModel mapToModel(NewTransactionDTO newTransactionDTO){
        return TransactionModel.builder()
                .type(newTransactionDTO.getType())
                .amount(newTransactionDTO.getAmount())
                .parent_id(newTransactionDTO.getParentId())
                .build();
    }
    
}

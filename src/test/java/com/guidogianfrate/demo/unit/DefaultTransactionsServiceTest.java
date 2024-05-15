package com.guidogianfrate.demo.unit;


import com.guidogianfrate.demo.dto.NewTransactionDTO;
import com.guidogianfrate.demo.dto.SumResponse;
import com.guidogianfrate.demo.exception.TransactionNotFoundException;
import com.guidogianfrate.demo.model.TransactionModel;
import com.guidogianfrate.demo.repository.TransactionRepository;
import com.guidogianfrate.demo.service.DefaultTransactionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DefaultTransactionsServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private DefaultTransactionsService transactionsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNewTransactionShouldSaveTransaction() {
        Long id = 1L;
        NewTransactionDTO newTransactionDTO = NewTransactionDTO.builder()
                .amount(100.0)
                .type("shopping")
                .build();

        transactionsService.createNewTransaction(id, newTransactionDTO);
        verify(transactionRepository, times(1)).save(anyLong(), any());
    }
    @Test
    void getTransactionsIdsByTypeShouldReturnTransactionIds() {
        String type = "shopping";
        TransactionModel transaction1 =TransactionModel.builder()
                .id(1L)
                .type(type)
                .build();

        TransactionModel transaction2 = TransactionModel.builder()
                .id(2L)
                .type(type)
                .build();

        when(transactionRepository.findByType(type)).thenReturn(List.of(transaction1, transaction2));

        List<Long> result = transactionsService.getTransactionsIdsByType(type);

        assertEquals(2, result.size());
        assertTrue(result.contains(1L));
        assertTrue(result.contains(2L));
    }

    @Test
    void getTransactionsIdsByTypeShouldReturnEmptyListWhenNoTransactionsFound() {
        String type = "shopping";

        when(transactionRepository.findByType(type)).thenReturn(Collections.emptyList());

        List<Long> result = transactionsService.getTransactionsIdsByType(type);

        assertTrue(result.isEmpty());
    }

    @Test
    void getTotalAmountTransitive_ShouldReturnTotalAmount() throws TransactionNotFoundException {
        Long id = 1L;
        TransactionModel transaction =TransactionModel.builder()
                .id(id)
                .amount(100.0)
                .build();

        TransactionModel childTransaction =TransactionModel.builder()
                .id(2L)
                .amount(50.0)
                .parentId(Optional.ofNullable(id))
                .build();

        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));
        when(transactionRepository.findByParentId(id)).thenReturn(List.of(childTransaction));
        when(transactionRepository.findByParentId(2L)).thenReturn(Collections.emptyList());

        SumResponse result = transactionsService.getTotalAmountTransitive(id);

        assertEquals(150.0, result.getSum());
    }

    @Test
    void getTotalAmountTransitive_ShouldThrowExceptionWhenTransactionNotFound() {
        Long id = 1L;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(TransactionNotFoundException.class, () -> transactionsService.getTotalAmountTransitive(id));
    }
}

package com.guidogianfrate.demo.dto;

import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewTransactionDTO {
    private double amount;
    private String type;
    private Optional<Long> parentId;
}

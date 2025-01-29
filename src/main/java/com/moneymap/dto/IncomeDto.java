package com.moneymap.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDto {
    private Long incomeId;
    private Long userId;
    private Long categoryId;
    private Double amount;
    private LocalDate incomeDate;
    private String description;
    private LocalDateTime createdAt;
}
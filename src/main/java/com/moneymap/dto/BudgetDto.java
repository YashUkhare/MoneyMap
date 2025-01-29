package com.moneymap.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDto {
    private Long budgetId;
    private Long userId;
    private Long categoryId;
    private Double totalBudget;
    private LocalDate startDate;
    private LocalDate endDate;
}
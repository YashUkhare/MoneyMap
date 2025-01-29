package com.moneymap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymap.dto.BudgetDto;
import com.moneymap.entity.Budget;
import com.moneymap.entity.ExpenseCategory;
import com.moneymap.entity.User;
import com.moneymap.repository.BudgetRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BudgetService {
    @Autowired
    private BudgetRepository budgetRepository;

    public void createBudget(BudgetDto budgetDTO) {
        Budget budget = Budget.builder()
            .user(User.builder().userId(budgetDTO.getUserId()).build())
            .category(ExpenseCategory.builder().categoryId(budgetDTO.getCategoryId()).build())
            .totalBudget(budgetDTO.getTotalBudget())
            .startDate(budgetDTO.getStartDate())
            .endDate(budgetDTO.getEndDate())
            .createdAt(java.time.LocalDateTime.now())
            .updatedAt(java.time.LocalDateTime.now())
            .build();
        budgetRepository.save(budget);
    }

    public List<Budget> getUserBudgets(Long userId) {
        return budgetRepository.findAll().stream()
            .filter(budget -> budget.getUser().getUserId().equals(userId))
            .collect(Collectors.toList());
    }
}
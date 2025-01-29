package com.moneymap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneymap.customException.ResourceNotFoundException;
import com.moneymap.dto.BudgetDto;
import com.moneymap.entity.Budget;
import com.moneymap.entity.ExpenseCategory;
import com.moneymap.entity.User;
import com.moneymap.repository.BudgetRepository;
import com.moneymap.repository.ExpenseCategoryRepository;
import com.moneymap.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final ExpenseCategoryRepository categoryRepository;

    public List<BudgetDto> getUserBudgets(Long userId) {
        return budgetRepository.findByUserUserId(userId)
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public BudgetDto setBudget(BudgetDto budgetDTO) {
        User user = userRepository.findById(budgetDTO.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ExpenseCategory category = categoryRepository.findById(budgetDTO.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Budget budget = Budget.builder()
            .user(user)
            .category(category)
            .totalBudget(budgetDTO.getTotalBudget())
            .startDate(budgetDTO.getStartDate())
            .endDate(budgetDTO.getEndDate())
            .build();

        Budget savedBudget = budgetRepository.save(budget);
        return convertToDTO(savedBudget);
    }

    private BudgetDto convertToDTO(Budget budget) {
        return BudgetDto.builder()
            .budgetId(budget.getBudgetId())
            .userId(budget.getUser().getUserId())
            .categoryId(budget.getCategory().getCategoryId())
            .totalBudget(budget.getTotalBudget())
            .startDate(budget.getStartDate())
            .endDate(budget.getEndDate())
            .build();
    }
}

package com.moneymap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymap.customException.ResourceNotFoundException;
import com.moneymap.entity.Expense;
import com.moneymap.repository.ExpenseRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    
    public Expense createExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId); 
    }
    
    public List<Expense> getExpensesByUserIdAndCategoryId(Long userId, Long categoryId) {
        return expenseRepository.findByUserIdAndCategoryId(userId, categoryId);
    }
}

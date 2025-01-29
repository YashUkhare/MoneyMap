package com.moneymap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymap.entity.Income;
import com.moneymap.repository.IncomeRepository;

import jakarta.transaction.Transactional;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Transactional
    public Income createIncome(Income income) {
        return incomeRepository.save(income);
    }

    public List<Income> getIncomesByUserId(Long userId) {
        return incomeRepository.findByUserId(userId); // Add filtering logic
    }
    
    public List<Income> getIncomeByUserIdAndCategoryId(Long userId, Long categoryId) {
        return incomeRepository.findByUserIdAndCategoryId(userId, categoryId);
    }
}
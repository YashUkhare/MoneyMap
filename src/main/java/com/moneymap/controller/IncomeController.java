package com.moneymap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneymap.entity.Income;
import com.moneymap.service.IncomeService;

@RestController
@RequestMapping("/api/incomes")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public ResponseEntity<Income> createIncome(@RequestBody Income income) {
        return ResponseEntity.ok(incomeService.createIncome(income));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Income>> getIncomesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(incomeService.getIncomesByUserId(userId));
    }
    
    @GetMapping("/user/{userId}/category/{categoryId}")
    public List<Income> getUserIncomeByCategoryId(@PathVariable Long userId, @PathVariable Long categoryId) {
        return incomeService.getIncomeByUserIdAndCategoryId(userId, categoryId);
    }
}
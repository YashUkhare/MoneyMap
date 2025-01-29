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

import com.moneymap.dto.BudgetDto;
import com.moneymap.entity.Budget;
import com.moneymap.service.BudgetService;

@RestController
@RequestMapping("/budgets")
public class BudgetController {
    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<String> createBudget(@RequestBody BudgetDto budgetDTO) {
        budgetService.createBudget(budgetDTO);
        return ResponseEntity.ok("Budget created successfully");
    }

    @GetMapping("/user/{userId}")
    public List<Budget> getUserBudgets(@PathVariable Long userId) {
        return budgetService.getUserBudgets(userId);
    }
}
package com.moneymap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneymap.dto.BudgetDto;
import com.moneymap.service.BudgetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<BudgetDto>> getUserBudgets(@PathVariable Long userId) {
        return ResponseEntity.ok(budgetService.getUserBudgets(userId));
    }

    @PostMapping
    public ResponseEntity<BudgetDto> setBudget(@RequestBody BudgetDto budgetDTO) {
        return ResponseEntity.ok(budgetService.setBudget(budgetDTO));
    }
}

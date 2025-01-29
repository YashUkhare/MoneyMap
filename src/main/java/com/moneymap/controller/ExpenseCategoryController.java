package com.moneymap.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneymap.dto.ExpenseCategoryDto;
import com.moneymap.service.ExpenseCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense-categories")
@RequiredArgsConstructor
public class ExpenseCategoryController {
    private final ExpenseCategoryService expenseCategoryService;

    @GetMapping
    public ResponseEntity<List<ExpenseCategoryDto>> getAllCategories() {
        return ResponseEntity.ok(expenseCategoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<ExpenseCategoryDto> createCategory(@RequestBody ExpenseCategoryDto dto) {
        return ResponseEntity.ok(expenseCategoryService.createCategory(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        expenseCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
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

import com.moneymap.dto.IncomeCategoryDto;
import com.moneymap.service.IncomeCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/income-categories")
@RequiredArgsConstructor
public class IncomeCategoryController {
    private final IncomeCategoryService incomeCategoryService;

    @GetMapping
    public ResponseEntity<List<IncomeCategoryDto>> getAllCategories() {
        return ResponseEntity.ok(incomeCategoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<IncomeCategoryDto> createCategory(@RequestBody IncomeCategoryDto dto) {
        return ResponseEntity.ok(incomeCategoryService.createCategory(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        incomeCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
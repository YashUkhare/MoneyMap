package com.moneymap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.moneymap.customException.ResourceNotFoundException;
import com.moneymap.dto.ExpenseCategoryDto;
import com.moneymap.entity.ExpenseCategory;
import com.moneymap.repository.ExpenseCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;

    public List<ExpenseCategoryDto> getAllCategories() {
        return expenseCategoryRepository.findAll().stream()
                .map(category -> new ExpenseCategoryDto(
                        category.getCategoryId(),
                        category.getCategoryName(),
                        category.getCreatedAt()
                )).collect(Collectors.toList());
    }

    public ExpenseCategoryDto createCategory(ExpenseCategoryDto dto) {
        ExpenseCategory category = new ExpenseCategory();
        category.setCategoryName(dto.getCategoryName());
        ExpenseCategory savedCategory = expenseCategoryRepository.save(category);
        return new ExpenseCategoryDto(
                savedCategory.getCategoryId(),
                savedCategory.getCategoryName(),
                savedCategory.getCreatedAt()
        );
    }

    public void deleteCategory(Long categoryId) {
        if (!expenseCategoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Expense Category not found");
        }
        expenseCategoryRepository.deleteById(categoryId);
    }
}
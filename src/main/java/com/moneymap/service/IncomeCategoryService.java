package com.moneymap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.moneymap.customException.ResourceNotFoundException;
import com.moneymap.dto.IncomeCategoryDto;
import com.moneymap.entity.IncomeCategory;
import com.moneymap.repository.IncomeCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncomeCategoryService {
    private final IncomeCategoryRepository incomeCategoryRepository;

    public List<IncomeCategoryDto> getAllCategories() {
        return incomeCategoryRepository.findAll().stream()
                .map(category -> new IncomeCategoryDto(
                        category.getCategoryId(),
                        category.getCategoryName(),
                        category.getCreatedAt()
                )).collect(Collectors.toList());
    }

    public IncomeCategoryDto createCategory(IncomeCategoryDto dto) {
        IncomeCategory category = new IncomeCategory();
        category.setCategoryName(dto.getCategoryName());
        IncomeCategory savedCategory = incomeCategoryRepository.save(category);
        return new IncomeCategoryDto(
                savedCategory.getCategoryId(),
                savedCategory.getCategoryName(),
                savedCategory.getCreatedAt()
        );
    }

    public void deleteCategory(Long categoryId) {
        if (!incomeCategoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Income Category not found");
        }
        incomeCategoryRepository.deleteById(categoryId);
    }
}
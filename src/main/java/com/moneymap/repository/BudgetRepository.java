package com.moneymap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moneymap.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
	List<Budget> findByUserUserId(Long userId);
    List<Budget> findByUserUserIdAndCategoryCategoryId(Long userId, Long categoryId);
    Budget findByUserIdAndCategoryId(Long userId, Long categoryId);
}

package com.moneymap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moneymap.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
	@Query("SELECT e FROM Expense e WHERE e.user.id = :userId")
	List<Expense> findByUserId(Long userId);
    
	@Query(value = "SELECT * FROM expenses WHERE user_id = :userId AND category_id = :categoryId", nativeQuery = true)
	List<Expense> findByUserIdAndCategoryId(Long userId, Long categoryId);
	
	@Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.user.userId = :userId AND e.category.categoryId = :categoryId")
	Double getTotalExpenseByCategory(Long userId, Long categoryId);
}

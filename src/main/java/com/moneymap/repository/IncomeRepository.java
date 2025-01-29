package com.moneymap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.moneymap.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
	@Query("SELECT i FROM Income i WHERE i.user.id = :userId")
	List<Income> findByUserId(Long userId);
    
	@Query(value = "SELECT * FROM incomes WHERE user_id = :userId AND category_id = :categoryId", nativeQuery = true)
	List<Income> findByUserIdAndCategoryId(Long userId, Long categoryId);
}

package com.moneymap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moneymap.entity.ExpenseCategory;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {}

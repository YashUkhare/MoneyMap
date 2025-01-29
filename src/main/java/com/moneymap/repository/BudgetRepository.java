package com.moneymap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moneymap.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {}

package com.moneymap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moneymap.entity.IncomeCategory;

@Repository
public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Long> {}



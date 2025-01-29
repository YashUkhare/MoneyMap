package com.moneymap.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ExpenseCategory category;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    @CreationTimestamp
    private Date expenseDate;

    @Column(length = 255)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
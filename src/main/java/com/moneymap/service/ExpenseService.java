package com.moneymap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymap.entity.Budget;
import com.moneymap.entity.Expense;
import com.moneymap.entity.Notification;
import com.moneymap.repository.BudgetRepository;
import com.moneymap.repository.ExpenseRepository;
import com.moneymap.repository.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExpenseService {
	
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private NotificationService notificationService;

    public Expense createExpense(Expense expense) {
        Expense savedExpense = expenseRepository.save(expense);
        
        // Check budget limit
        checkSoftLimit(savedExpense);
        // Budget Check Logic
        checkBudgetAndNotify(expense);

        return savedExpense;
    }

    public List<Expense> getExpensesByUserId(Long userId) {
        return expenseRepository.findByUserId(userId); 
    }
    
    public List<Expense> getExpensesByUserIdAndCategoryId(Long userId, Long categoryId) {
        return expenseRepository.findByUserIdAndCategoryId(userId, categoryId);
    }

    private void checkBudgetAndNotify(Expense expense) {
        List<Budget> budgets = budgetRepository.findByUserUserIdAndCategoryCategoryId(expense.getUser().getUserId(), expense.getCategory().getCategoryId());

        if (!budgets.isEmpty()) {
            Budget budget = budgets.get(0);
            Double totalExpenses = expenseRepository.getTotalExpenseByCategory(expense.getUser().getUserId(), expense.getCategory().getCategoryId());

            if (totalExpenses > budget.getTotalBudget()) {
                Notification notification = new Notification();
                notification.setUser(expense.getUser());
                notification.setMessage("Budget exceeded for category: " + budget.getCategory().getCategoryName());
                notificationRepository.save(notification);
            }
        }
    }
    
    private void checkSoftLimit(Expense expense) {
        Long userId = expense.getUser().getUserId();
        Long categoryId = expense.getCategory().getCategoryId();

        // Fetch budget for this category
        Budget budget = budgetRepository.findByUserIdAndCategoryId(userId, categoryId);

        if (budget == null) return; // No budget set for this category

        // Get total expenses for the category
        Double totalExpenses = expenseRepository.getTotalExpenseByCategory(userId, categoryId);

        // Calculate soft limit threshold
        Double softLimitAmount = (budget.getSoftLimitPercentage() / 100) * budget.getTotalBudget();

        if (totalExpenses >= softLimitAmount) {
            // Trigger a notification
            String message = "Warning: You have exceeded " + budget.getSoftLimitPercentage() +
                             "% of your budget for " + expense.getCategory().getCategoryName();

            notificationService.createNotification(userId, message);
        }
    }
}

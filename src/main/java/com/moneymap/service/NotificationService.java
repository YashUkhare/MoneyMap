package com.moneymap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymap.customException.ResourceNotFoundException;
import com.moneymap.entity.Notification;
import com.moneymap.entity.User;
import com.moneymap.repository.NotificationRepository;
import com.moneymap.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificationService {
	
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository; 
    
    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findAll().stream()
            .filter(notification -> notification.getUser().getUserId().equals(userId))
            .collect(Collectors.toList());
    }
    
    public void createNotification(Long userId, String message) {
    	
    	User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    	
        Notification notification = Notification.builder()
                .user(user)
                .message(message)
                .isRead(false)
                .build();
        
        notificationRepository.save(notification);
    }
}
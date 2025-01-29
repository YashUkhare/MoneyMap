package com.moneymap.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymap.dto.NotificationDTO;
import com.moneymap.entity.Notification;
import com.moneymap.entity.User;
import com.moneymap.repository.NotificationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification(NotificationDTO notificationDTO) {
        Notification notification = Notification.builder()
            .user(User.builder().userId(notificationDTO.getUserId()).build())
            .message(notificationDTO.getMessage())
            .isRead(false)
            .createdAt(java.time.LocalDateTime.now())
            .build();
        notificationRepository.save(notification);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findAll().stream()
            .filter(notification -> notification.getUser().getUserId().equals(userId))
            .collect(Collectors.toList());
    }
}
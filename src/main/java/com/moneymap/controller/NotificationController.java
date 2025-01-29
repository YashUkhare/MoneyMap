package com.moneymap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moneymap.dto.NotificationDTO;
import com.moneymap.entity.Notification;
import com.moneymap.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody NotificationDTO notificationDTO) {
        notificationService.createNotification(notificationDTO);
        return ResponseEntity.ok("Notification created successfully");
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getUserNotifications(@PathVariable Long userId) {
        return notificationService.getUserNotifications(userId);
    }
}
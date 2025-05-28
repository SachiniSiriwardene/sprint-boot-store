package com.example.store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificationManager {
    public NotificationService notificationService;

    public NotificationManager(@Qualifier("Email") NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification(String message) {
        notificationService.sendMessage(message);
    }
}

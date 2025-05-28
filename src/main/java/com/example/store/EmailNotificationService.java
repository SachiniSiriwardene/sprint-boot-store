package com.example.store;

import org.springframework.stereotype.Service;

@Service("Email")
public class EmailNotificationService implements NotificationService{
    @Override
    public void sendMessage(String message) {
        System.out.println("Sending email :"+message);
    }
}

package com.learntocodewithluis.spring_boot_demo_store;

import org.springframework.stereotype.Service;

@Service("email")
public class EmailNotificationService implements NotificationService{
    @Override
    public void send(String message) {
        System.out.println("Sending email : " + message);
    }
}

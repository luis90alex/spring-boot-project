package com.learntocodewithluis.spring_boot_demo_store.userregistration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService{

    @Value("${user-registration.host}")
    private String host;

    @Value("${user-registration.port}")
    private int port;

    @Override
    public void send(String message, String recipientEmail) {
        System.out.println("Sending message :" + message);
        System.out.println("Email :" + recipientEmail);
        System.out.println("Port :" + port);
        System.out.println("Host :" + host);
    }
}

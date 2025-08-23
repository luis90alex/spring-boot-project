package com.learntocodewithluis.spring_boot_demo_store;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("sms")
@Primary
public class SMSNotificationService implements NotificationService{
    @Override
    public void send(String message) {
        System.out.println("Sending SMS : " + message);
    }
}

package com.learntocodewithluis.spring_boot_demo_store.userregistration;

public interface NotificationService {
    void send(String message, String recipientEmail);
}

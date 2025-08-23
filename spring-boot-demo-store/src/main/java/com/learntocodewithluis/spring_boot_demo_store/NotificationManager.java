package com.learntocodewithluis.spring_boot_demo_store;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificationManager {

    private final NotificationService notificationService;

    //@Autowired
    public NotificationManager(@Qualifier("email") NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public void sendNotification(String message){
        notificationService.send(message);
    }
}

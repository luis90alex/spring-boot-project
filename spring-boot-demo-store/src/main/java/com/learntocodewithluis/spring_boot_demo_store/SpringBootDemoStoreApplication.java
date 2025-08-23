package com.learntocodewithluis.spring_boot_demo_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemoStoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootDemoStoreApplication.class, args);
		var notificationManager = context.getBean(NotificationManager.class);
		notificationManager.sendNotification("Hello, this is a test message!");
	}

}

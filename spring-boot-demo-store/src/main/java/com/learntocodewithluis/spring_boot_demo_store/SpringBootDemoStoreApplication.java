package com.learntocodewithluis.spring_boot_demo_store;

import com.learntocodewithluis.spring_boot_demo_store.userregistration.User;
import com.learntocodewithluis.spring_boot_demo_store.userregistration.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemoStoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootDemoStoreApplication.class, args);
		var notificationManager = context.getBean(NotificationManager.class);
		notificationManager.sendNotification("Hello, this is a test message!");
		var orderService = context.getBean(OrderService.class);
		orderService.placeOrder();

		User user = new User("test-name", (long) 23, "email@mail.com","pass");
		var userService = context.getBean(UserService.class);
		userService.registerUser(user);
	}

}

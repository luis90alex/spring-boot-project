package com.learntocodewithluis.spring_boot_demo_store;


import com.learntocodewithluis.spring_boot_demo_store.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDemoStoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootDemoStoreApplication.class, args);
		var user = User.builder()
				.name(("Luis"))
				.password("pass")
				.email("test@gmail.com")
				.build();
	}

}

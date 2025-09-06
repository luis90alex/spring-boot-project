package com.learntocodewithluis.spring_boot_demo_store;


import com.learntocodewithluis.spring_boot_demo_store.entities.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringBootDemoStoreApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootDemoStoreApplication.class, args);
		var user = User.builder()
				.name(("Luis"))
				.password("pass")
				.email("test@gmail.com")
				.build();

		user.addTag("tag1");
		var profile = Profile.builder()
				.bio("bio")
				.build();
		user.setProfile(profile);
		profile.setUser(user);
		System.out.println(user);
	}

}

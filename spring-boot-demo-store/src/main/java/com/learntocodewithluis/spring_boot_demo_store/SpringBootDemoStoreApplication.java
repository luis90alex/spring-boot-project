package com.learntocodewithluis.spring_boot_demo_store;


import com.learntocodewithluis.spring_boot_demo_store.entities.*;
import com.learntocodewithluis.spring_boot_demo_store.repositories.UserRepository;
import com.learntocodewithluis.spring_boot_demo_store.services.UserService;
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
        //var repository = context.getBean(UserRepository.class);
        //repository.deleteById(1L);
        var service = context.getBean(UserService.class);
        //service.showEntityStates();
        //service.persistRelated();
        //service.deleteRelated();
        //service.addNewProductNewCategory();
        //service.addNewProductForExistingCategory();
        //service.addNewProductsToUserWishlist();
        //service.deleteExistingProduct();
        //service.deleteExistingProductWithPreviousModification();
        //service.updatePriceByCategory();
        //service.fetchProducts();
        //service.fetchUser();
        //service.fetchUsers();
        //service.fetchProductsProcedure();
        //service.addUserAndProfile();
        //service.fetchProfilesGreaterThan();
        //service.fetchProfilesGreaterThanFromUser();
        //service.fetchProductsByExample();
        //service.fetchProductsByCriteriaAPI();
        //service.fetchProductsBySpecificationAPI("Product", BigDecimal.valueOf(1), BigDecimal.valueOf(200));
        //service.fetchSortedProducts();
        //service.fetchPaginatedProducts(3,4);
        //service.fetchProductsByCategoryCriteriaAPI();
        service.fetchProductsByCategorySpecificationAPI((byte) 1);
	}

}

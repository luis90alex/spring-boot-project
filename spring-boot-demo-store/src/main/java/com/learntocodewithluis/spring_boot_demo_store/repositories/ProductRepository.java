package com.learntocodewithluis.spring_boot_demo_store.repositories;

import com.learntocodewithluis.spring_boot_demo_store.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
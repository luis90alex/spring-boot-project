package com.learntocodewithluis.spring_boot_demo_store.repositories;

import com.learntocodewithluis.spring_boot_demo_store.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
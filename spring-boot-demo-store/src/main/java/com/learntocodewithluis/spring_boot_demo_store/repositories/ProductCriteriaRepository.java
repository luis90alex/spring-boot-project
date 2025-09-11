package com.learntocodewithluis.spring_boot_demo_store.repositories;

import com.learntocodewithluis.spring_boot_demo_store.entities.Category;
import com.learntocodewithluis.spring_boot_demo_store.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> findProductsByCriteria(String name, BigDecimal min, BigDecimal max);
    List<Product> findProductsByCategory(Byte category_id);
}

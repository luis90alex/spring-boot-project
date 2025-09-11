package com.learntocodewithluis.spring_boot_demo_store.repositories.specifications;

import com.learntocodewithluis.spring_boot_demo_store.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpec {
    public static Specification<Product> hasName(String name){
        return ((root, query, cb) -> cb.like(root.get("name"), "%" + name + "%"));
    }

    public static Specification<Product> hasPriceGreaterThanOrEqualTo(BigDecimal price){
        return ((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> hasPriceLessThanOrEqualTo(BigDecimal price){
        return ((root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> filterByCategory(byte category_id){
        return ((root, query, cb) -> cb.equal(root.get("category").get("id"), category_id));
    }
}

package com.learntocodewithluis.spring_boot_demo_store.repositories;

import com.learntocodewithluis.spring_boot_demo_store.dtos.ProductSummary;
import com.learntocodewithluis.spring_boot_demo_store.entities.Category;
import com.learntocodewithluis.spring_boot_demo_store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository, JpaSpecificationExecutor<Product> {

    @Query(value = "select * from products p where p.price between :min and :max order by name", nativeQuery = true)
    List<Product> findProductsSQL(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query(value = "select p from Product p where p.price between :min and :max order by p.name")
    List<Product> findProductsJPQL(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query(value = "select count (*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);


    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId" )
    void updatePriceByCategory(@Param("newPrice") BigDecimal newPrice, @Param("categoryId") Byte categoryId);

    List<ProductSummary> findByCategory(Category category);

    @Procedure("findProductsByPrice")
    List<Product> findProductsByPrice(BigDecimal min, BigDecimal max);
}
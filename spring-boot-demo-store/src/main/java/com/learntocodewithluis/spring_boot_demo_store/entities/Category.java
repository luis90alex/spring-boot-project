package com.learntocodewithluis.spring_boot_demo_store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name ="id")
    private Byte id;

    @Column(nullable = false, name ="name")
    private String name;

    @OneToMany(mappedBy = "category")
    @Builder.Default
    private Set<Product> products = new HashSet<>();


    public void addProduct(Product product){
        products.add(product);
        product.setCategory(this);
    }

    public void removeAddress(Product product){
        products.remove(product);
        product.setCategory(null);
    }
}

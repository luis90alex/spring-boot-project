package com.learntocodewithluis.spring_boot_demo_store.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name ="street")
    private String street;

    @Column(nullable = false, name ="city")
    private String city;

    @Column(nullable = false, name ="zip")
    private String zip;

    @Column(nullable = false, name ="state")
    private String state;
}

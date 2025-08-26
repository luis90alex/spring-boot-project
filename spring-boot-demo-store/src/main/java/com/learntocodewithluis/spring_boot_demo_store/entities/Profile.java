package com.learntocodewithluis.spring_boot_demo_store.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name ="bio")
    private String bio;

    @Column(nullable = false, name ="phone_number")
    private String phoneNumber;

    @Column(nullable = false, name ="date_of_birth")
    private LocalDate dateOfBirth;

    @Column(nullable = false, name ="loyalty_points")
    private Integer loyaltyPoints;
}

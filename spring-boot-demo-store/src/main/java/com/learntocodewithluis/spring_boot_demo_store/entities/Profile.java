package com.learntocodewithluis.spring_boot_demo_store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    @ToString.Exclude
    private User user;
}

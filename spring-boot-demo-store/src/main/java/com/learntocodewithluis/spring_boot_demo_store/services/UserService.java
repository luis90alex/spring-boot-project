package com.learntocodewithluis.spring_boot_demo_store.services;

import com.learntocodewithluis.spring_boot_demo_store.entities.User;
import com.learntocodewithluis.spring_boot_demo_store.repositories.AddressRepository;
import com.learntocodewithluis.spring_boot_demo_store.repositories.ProfileRepository;
import com.learntocodewithluis.spring_boot_demo_store.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final ProfileRepository profileRepository;
    private final AddressRepository addressRepository;

    public void showEntityStates(){
        var user = User.builder()
                .name("Luis test")
                .email("ga@gmail.com")
                .password("e34")
                .build();

        if (entityManager.contains(user)){
            System.out.println("Persistent state");
        }else{
            System.out.println("Transient or detached");
        }
        userRepository.save(user);
        if (entityManager.contains(user)){
            System.out.println("Persistent state");
        }else{
            System.out.println("Transient or detached");
        }
    }

    @Transactional
    public void showRelatedEntities(){
        var profile = profileRepository.findById(1L).orElseThrow();
        System.out.println(profile.getUser().getEmail());

    }

    public void fetchAddress(){
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println("Address: " + address);
    }
}

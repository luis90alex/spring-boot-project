package com.learntocodewithluis.spring_boot_demo_store.repositories;

import com.learntocodewithluis.spring_boot_demo_store.entities.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
package com.learntocodewithluis.spring_boot_demo_store.repositories;

import com.learntocodewithluis.spring_boot_demo_store.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
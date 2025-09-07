package com.learntocodewithluis.spring_boot_demo_store.repositories;

import com.learntocodewithluis.spring_boot_demo_store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
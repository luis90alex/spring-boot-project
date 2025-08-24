package com.learntocodewithluis.spring_boot_demo_store.userregistration;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}

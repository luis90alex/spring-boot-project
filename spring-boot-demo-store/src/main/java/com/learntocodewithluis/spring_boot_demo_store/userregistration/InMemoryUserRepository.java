package com.learntocodewithluis.spring_boot_demo_store.userregistration;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository{

    private final Map<String, User> usersInMemory = new HashMap<>();
    @Override
    public void save(User user) {
        System.out.println("Saving in memory the user :" + user);
        usersInMemory.put(user.getEmail(), user);
    }

    @Override
    public User findByEmail(String email) {
        return usersInMemory.getOrDefault(email, null);
    }
}

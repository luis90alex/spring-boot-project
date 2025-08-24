package com.learntocodewithluis.spring_boot_demo_store.userregistration;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public UserService(UserRepository userRepository, NotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @PreDestroy
    public void destroy (){
        System.out.println("User saved");
        System.out.println("Notification sended");
    }

    public void registerUser(User user){

        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new IllegalArgumentException("Duplicated email. Email : " + user.getEmail());
        }
        userRepository.save(user);
        notificationService.send("Registration successfully", user.getEmail());
    }
}

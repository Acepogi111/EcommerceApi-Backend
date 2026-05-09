package com.ws101.ada.fajardo.EcommerceApi.config;

import com.ws101.ada.fajardo.EcommerceApi.entity.User;
import com.ws101.ada.fajardo.EcommerceApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN");
            User user = new User("ace", passwordEncoder.encode("user123"), "USER");
            
            userRepository.save(admin);
            userRepository.save(user);
            
            System.out.println(">>> Created users: admin/admin123 and ace/user123");
        } else {
            System.out.println(">>> Users already exist, skipping seed");
        }
    }
}
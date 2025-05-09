package com.example.loginauthapi.services;

import com.example.loginauthapi.domain.role.Role;
import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.repositories.RoleRepository;
import com.example.loginauthapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User admin = null;
        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            Role dminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

            admin = new User();
            admin.setName("Admin");
            admin.setEmail("${adminEmail}");
            admin.setPassword(passwordEncoder.encode("${adminPassword}"));
            admin.getRoles().add(dminRole);

            userRepository.save(admin);

            System.out.println("✅ Admin user created");
        }


    }
}

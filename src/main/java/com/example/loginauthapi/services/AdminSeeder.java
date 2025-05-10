package com.example.loginauthapi.services;

import com.example.loginauthapi.domain.role.Role;
import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.repositories.RoleRepository;
import com.example.loginauthapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${admEmail}")
    private String adminEmail;

    @Value("${admPassword}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.findByEmail(adminEmail).isEmpty()){
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(()-> new RuntimeException("Role Admin not found"));

            User admin = new User();
            admin.setName("Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));

            admin.getRoles().add(adminRole);
            userRepository.save(admin);

            System.out.println("✅ Admin user created");

        }
    }
}

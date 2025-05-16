package com.example.loginauthapi.controllers;

import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.dto.LoginRequestDTO;
import com.example.loginauthapi.dto.ResponseDTO;
import com.example.loginauthapi.infra.security.TokenService;
import com.example.loginauthapi.repositories.RoleRepository;
import com.example.loginauthapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminAuthController {


    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginRequestDTO body){
        User user = this.userRepository.findByEmail(body.email())
                .orElseThrow(()-> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(body.password(), user.getPassword()))
            return ResponseEntity.badRequest().body("Invalid Credentials");

        boolean isAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_ADMIN"));

        if (!isAdmin){
            return ResponseEntity.status(403).body("Access Denied");
        }

        String token = this.tokenService.generateToken(user);
        String userLogged = "Admin logged successfully";
        System.out.println(userLogged + " name: " + user.getName());

        return ResponseEntity.ok(new ResponseDTO(userLogged,token));
    }
}

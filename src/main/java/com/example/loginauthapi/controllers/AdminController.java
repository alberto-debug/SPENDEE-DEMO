package com.example.loginauthapi.controllers;


import com.example.loginauthapi.domain.user.Task;
import com.example.loginauthapi.domain.user.Transaction;
import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.dto.UserSummary;
import com.example.loginauthapi.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public List<UserSummary> getAllUsers(){
        return adminService.getAllUsers();
    }

    @GetMapping("/users/{email}/transactions")
    public List<Transaction> getUserTransactions(@PathVariable String email){
        return  adminService.getUserTransactions(email);
    }

    @GetMapping("/users/{email}/tasks")
    public List<Task> getUserTasks(@PathVariable String email){
        return adminService.getUserTasks(email);
    }

}

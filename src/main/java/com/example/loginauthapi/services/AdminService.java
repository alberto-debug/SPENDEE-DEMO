package com.example.loginauthapi.services;

import com.example.loginauthapi.domain.user.Task;
import com.example.loginauthapi.domain.user.Transaction;
import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.dto.UserSummary;
import com.example.loginauthapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TaskService taskService;


    public List<UserSummary> getAllUsers(){
        return  userRepository.findAll().stream().map(user -> new UserSummary(user.getId() , user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public List<Transaction> getUserTransactions(String userEmail){
        return transactionService.getUserTransaction(userEmail);
    }

    public List<Task> getUserTasks(String userEmail){
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(()-> new RuntimeException("user not found: " + userEmail));

        return taskService.getUserTasks(user.getId());
    }

}

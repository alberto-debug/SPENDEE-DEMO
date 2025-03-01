package com.example.loginauthapi.controllers;


import com.example.loginauthapi.domain.user.Task;
import com.example.loginauthapi.domain.user.User;
import com.example.loginauthapi.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Task> createTasks(@RequestBody Task task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        Optional<Task> task = taskService.getTakById(id);
        return task.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/tasks")
    public ResponseEntity<?> getUserTasks(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
        }

        return ResponseEntity.ok(taskService.getUserTasks(user.getId()));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getUserTaskById(@PathVariable Long id){
        return ResponseEntity.ok(taskService.getUserTasks(id));
    }



}

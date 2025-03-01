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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Task> createTasks(@RequestBody Task task, @AuthenticationPrincipal User user){
        task.setUser(user);
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/user")
    public ResponseEntity<List<Task>> getUserTasks(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(taskService.getUserTasks(user.getId()));
    }

    @PutMapping("/updateTask")
    public ResponseEntity<Task> updateTask(
            @AuthenticationPrincipal User user,
            @RequestBody Task updatedTask) {

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Optional<Task> taskOptional = taskService.getTaskByUserAndTitle(user.getId(), updatedTask.getTitle());

        if (taskOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Task updated = taskService.updateTask(taskOptional.get(), updatedTask);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<Void> deleteTask(@RequestBody Task task, @AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<Task> taskOptional = taskService.getTaskByUserAndTitle(user.getId(), task.getTitle());

        if (taskOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        taskService.deleteTask(taskOptional.get());
        return ResponseEntity.noContent().build();
    }
}
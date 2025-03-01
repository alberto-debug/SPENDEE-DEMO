package com.example.loginauthapi.services;

import com.example.loginauthapi.domain.user.Task;
import com.example.loginauthapi.domain.user.TaskStatus;
import com.example.loginauthapi.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        task.setStatus(TaskStatus.ONGOING);
        return taskRepository.save(task);
    }
}

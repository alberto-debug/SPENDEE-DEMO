package com.example.loginauthapi.services;

import com.example.loginauthapi.domain.user.Task;
import com.example.loginauthapi.domain.user.TaskStatus;
import com.example.loginauthapi.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        task.setStatus(TaskStatus.ONGOING);
        return taskRepository.save(task);
    }

    public List<Task> getUserTasks(Long id){
        return taskRepository.findByUserId(id);
    }

    public Optional<Task> getTakById(Long id){
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task updatedTask){
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setDueDate(updatedTask.getDueDate());
            task.setStatus(updatedTask.getStatus());
            return taskRepository.save(task);
        }).orElseThrow(()-> new RuntimeException("Task Not Found"));
    }


    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}

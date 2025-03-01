package com.example.loginauthapi.repositories;

import com.example.loginauthapi.domain.user.Task;
import com.example.loginauthapi.domain.user.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByStatus(TaskStatus status);
}

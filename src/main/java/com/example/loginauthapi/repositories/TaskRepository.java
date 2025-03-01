package com.example.loginauthapi.repositories;

import com.example.loginauthapi.domain.user.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}

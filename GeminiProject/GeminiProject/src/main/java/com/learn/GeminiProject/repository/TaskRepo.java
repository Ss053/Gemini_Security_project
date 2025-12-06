package com.learn.GeminiProject.repository;

import com.learn.GeminiProject.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepo extends JpaRepository<Task, Long> {
}

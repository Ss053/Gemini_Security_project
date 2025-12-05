package com.learn.GeminiProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;

public interface TaskRepo extends JpaRepository<Task, Long> {
}

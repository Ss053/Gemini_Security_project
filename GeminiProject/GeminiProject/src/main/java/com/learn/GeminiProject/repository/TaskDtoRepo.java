package com.learn.GeminiProject.repository;

import com.learn.GeminiProject.DTO.TaskDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDtoRepo extends JpaRepository<TaskDto, Long> {
}

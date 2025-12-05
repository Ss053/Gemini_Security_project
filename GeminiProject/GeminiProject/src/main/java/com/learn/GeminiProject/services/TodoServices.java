package com.learn.GeminiProject.services;

import com.learn.GeminiProject.DTO.TaskDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoServices {
    public ResponseEntity<TaskDto> createTask(TaskDto task);
    public ResponseEntity<List<TaskDto>> getAllTasks();
    public ResponseEntity<TaskDto> getTask(int id);
    public ResponseEntity<TaskDto> updateTask(Long id ,TaskDto task);
    public ResponseEntity<TaskDto> deleteTask(Long id);
}

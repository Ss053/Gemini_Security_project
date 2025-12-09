package com.learn.GeminiProject.services;

import com.learn.GeminiProject.DTO.TaskDto;
import com.learn.GeminiProject.models.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoServices {
    public ResponseEntity<Task> createTask(TaskDto task);
    public ResponseEntity<List<Task>> getAllTasks();
    public ResponseEntity<TaskDto> getTask(Long id);
    public ResponseEntity<TaskDto> updateTask(Long id ,TaskDto task);
    public ResponseEntity<String> deleteTask(Long id);
}

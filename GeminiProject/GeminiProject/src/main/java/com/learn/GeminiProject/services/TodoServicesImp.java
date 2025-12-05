package com.learn.GeminiProject.services;

import com.learn.GeminiProject.DTO.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServicesImp implements TodoServices{

    @Override
    public ResponseEntity<TaskDto> createTask(TaskDto task) {
        return null;
    }

    @Override
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return null;
    }

    @Override
    public ResponseEntity<TaskDto> getTask(int id) {
        return null;
    }

    @Override
    public ResponseEntity<TaskDto> updateTask(Long id, TaskDto task) {
        return null;
    }

    @Override
    public ResponseEntity<TaskDto> deleteTask(Long id) {
        return null;
    }
}

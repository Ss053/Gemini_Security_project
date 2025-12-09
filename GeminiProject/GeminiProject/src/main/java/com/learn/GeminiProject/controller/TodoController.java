package com.learn.GeminiProject.controller;

import com.learn.GeminiProject.DTO.TaskDto;
import com.learn.GeminiProject.models.Task;
import com.learn.GeminiProject.services.TodoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class TodoController {
    private final TodoServices todoServices;

    //Create a new task for the authenticated user.
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody TaskDto task) {
        return todoServices.createTask(task);
    }

    //Retrieve all tasks belonging to the authenticated user.
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return todoServices.getAllTasks();
    }

    //Retrieve a specific task by its ID. (Must check ownership).
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {
        return todoServices.getTask(id);
    }

    //Update an existing task. (Must check ownership).
    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto task) {
        return todoServices.updateTask(id, task);
    }

    //Delete a specific task. (Must check ownership).
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        return todoServices.deleteTask(id);
    }
}

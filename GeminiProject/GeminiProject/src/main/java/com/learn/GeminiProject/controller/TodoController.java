package com.learn.GeminiProject.controller;

import com.learn.GeminiProject.DTO.TaskDto;
import com.learn.GeminiProject.models.Task;
import com.learn.GeminiProject.services.TodoServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return ResponseEntity.ok().body(new ArrayList<Task>());
    }

    //Retrieve a specific task by its ID. (Must check ownership).
    @GetMapping("/tasks/{id}")
    public ResponseEntity<String> getTask(@PathVariable Long id) {
        return ResponseEntity.ok().body("Task with id " + id);
    }

    //Update an existing task. (Must check ownership).
    @PutMapping("/task/{id}")
    public ResponseEntity<String> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return ResponseEntity.ok().body("Task with id " + id);
    }

    //Delete a specific task. (Must check ownership).
    @DeleteMapping("/task/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        return ResponseEntity.ok().body("Task with id " + id);
    }
}

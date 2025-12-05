package com.learn.GeminiProject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TodoController {

    //Create a new task for the authenticated user.
    @PostMapping("/tasks")
    public ResponseEntity<String> createTask(@RequestBody Task task) {
        return new ResponseEntity<>("", HttpStatus.CREATED);
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

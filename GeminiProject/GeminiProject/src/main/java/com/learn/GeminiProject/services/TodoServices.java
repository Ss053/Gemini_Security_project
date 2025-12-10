package com.learn.GeminiProject.services;

import com.learn.GeminiProject.DTO.TaskDto;
import com.learn.GeminiProject.models.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TodoServices {

    //Creat new Task
    public ResponseEntity<Task> createTask(TaskDto task);

    //Fetch all task
    public ResponseEntity<List<TaskDto>> getAllTasks();

    //Fetch task by Id
    public ResponseEntity<TaskDto> getTask(Long id);

    //Update task by id
    public ResponseEntity<TaskDto> updateTask(Long id ,TaskDto task);

    // Delete of Task by id
    public ResponseEntity<String> deleteTask(Long id);
}

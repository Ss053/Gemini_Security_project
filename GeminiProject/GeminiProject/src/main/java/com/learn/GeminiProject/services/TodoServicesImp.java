package com.learn.GeminiProject.services;

import com.learn.GeminiProject.DTO.TaskDto;
import com.learn.GeminiProject.models.Task;
import com.learn.GeminiProject.repository.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServicesImp implements TodoServices{
    private final TaskRepo taskRepo;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Task> createTask(TaskDto task) {
        Task newTask = modelMapper.map(task, Task.class);
        newTask.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        Task a = taskRepo.save(newTask);
        if (a.getId() > 0) {
            return new ResponseEntity<>(newTask, HttpStatus.CREATED);
        }else {
             throw new ServiceException("Task not created");
        }

    }

    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskRepo.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TaskDto> getTask(Long id) {
        TaskDto getTask = modelMapper.map(taskRepo.findById(id), TaskDto.class);
        return ResponseEntity.ok(getTask);
    }

    @Override
    public ResponseEntity<TaskDto> updateTask(Long id, TaskDto taskDto) {
        Task existingTask = taskRepo.findById(id)
                .orElseThrow(() -> new ServiceException("Task not found"));
        modelMapper.map(taskDto, existingTask);
        existingTask.setId(id);
        TaskDto getTask = modelMapper.map(taskRepo.save(existingTask), TaskDto.class);
        return new ResponseEntity<>(getTask, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> deleteTask(Long id) {
        if(taskRepo.existsById(id)){
            taskRepo.deleteById(id);
            return ResponseEntity.ok("Deleted the task");
        }else{
            throw new ServiceException("Task not found");
        }
    }
}

package com.learn.GeminiProject.services;

import com.learn.GeminiProject.DTO.TaskDto;
import com.learn.GeminiProject.models.Task;
import com.learn.GeminiProject.models.User;
import com.learn.GeminiProject.projectConfig.GeminiProjectUserDetails;
import com.learn.GeminiProject.repository.TaskRepo;
import com.learn.GeminiProject.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServicesImp implements TodoServices{
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;


    private GeminiProjectUserDetails getCurrentUserPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            // Throw a security-related exception, or handle the anonymous case
            throw new ServiceException("User is not authenticated.");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof GeminiProjectUserDetails customUser) {
            return customUser;
        } else {
            // This happens if the user is logged in but not using your custom UserDetails
            throw new ServiceException("Authentication principal type is incorrect.");
        }
    }

    //Creat new Task
    @Override
    public ResponseEntity<Task> createTask(TaskDto task) {
        Task newTask = modelMapper.map(task, Task.class);

        GeminiProjectUserDetails currentUser = getCurrentUserPrincipal();

        //Auto initialization of createdOn
        newTask.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
        newTask.setCompleted(false);
        User user = userRepo.findByUsername(currentUser.getUsername()).orElse(null);
        newTask.setUserId(user);

        Task a = taskRepo.save(newTask);

        //Check assigned task
        if (a.getId() > 0) {
            return new ResponseEntity<>(newTask, HttpStatus.CREATED);
        }else {
            //Exception
             throw new ServiceException("Task not created");
        }

    }

    //Fetch all task
    @Override
    public ResponseEntity<List<TaskDto>> getAllTasks() {

        GeminiProjectUserDetails currentUser = getCurrentUserPrincipal();

        User user = userRepo.findByUsername(currentUser.getUsername()).orElseThrow(() -> new ServiceException("User is not found."));
        //Stream for Task -> TaskDto
        List<TaskDto> tasks = user.getTasks().stream()
                .map(taskDto -> modelMapper.map(taskDto, TaskDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TaskDto>> getCompletedTask(boolean check) {
        GeminiProjectUserDetails currentUser = getCurrentUserPrincipal();

        User user = userRepo.findByUsername(currentUser.getUsername()).orElseThrow(() -> new ServiceException("User is not found."));

        List<TaskDto> tasks = user.getTasks().stream()
                .filter(task -> Boolean.valueOf(check).equals(task.getCompleted()))
                .map(y -> modelMapper.map(y, TaskDto.class))
                .toList();
        return new ResponseEntity<>(tasks, HttpStatus.FOUND);
    }

    //Fetch task by Id
    @Override
    public ResponseEntity<TaskDto> getTask(Long id)  {
        //Conversion Task -> TaskDto
        Task task = taskRepo.findById(id).orElseThrow(() -> new ServiceException("Task not found."));
        GeminiProjectUserDetails currentUser = getCurrentUserPrincipal();
        System.out.println(currentUser.getId());
        if(!task.getUserId().getId().equals(currentUser.getId())){
            throw new AccessDeniedException("unauthorised access");
        }
        TaskDto getTask = modelMapper.map(task, TaskDto.class);

        //Check Task is generated


        return ResponseEntity.ok(getTask);
    }

    //Update task by id
    @Override
    public ResponseEntity<TaskDto> updateTask(Long id, TaskDto taskDto) {
        // Check existence of task by id
        Task task = taskRepo.findById(id).orElseThrow(() -> new ServiceException("Task not found."));
        GeminiProjectUserDetails currentUser = getCurrentUserPrincipal();
        if(!task.getUserId().getId().equals(currentUser.getId())){
            throw new AccessDeniedException("unauthorised access");
        }


        // Conversion of TaskDto -> Task
        modelMapper.map(taskDto, task);

        // Assign id and Time of update
        task.setId(id);
        task.setUpdatedOn(Timestamp.valueOf(LocalDateTime.now()));

        //Conversion Task -> TaskDto
        TaskDto getTask = modelMapper.map(taskRepo.save(task), TaskDto.class);
        return new ResponseEntity<>(getTask, HttpStatus.ACCEPTED);
    }

    // Delete of Task by id
    @Override
    public ResponseEntity<String> deleteTask(Long id) {

        Task task = taskRepo.findById(id).orElseThrow(() -> new ServiceException("Task not found."));
        GeminiProjectUserDetails currentUser = getCurrentUserPrincipal();
        if(!task.getUserId().getId().equals(currentUser.getId())){
            throw new AccessDeniedException("unauthorised access");
        }
        //Check the existence of id and Delete

            //Delete task
            taskRepo.deleteById(id);
            return ResponseEntity.ok("Deleted the task");

    }

    //Delete all Task
    @Override
    public ResponseEntity<String> deleteAllTask() {

        //Delete
        taskRepo.deleteAll();

        return ResponseEntity.ok("Deleted all the tasks");
    }
}

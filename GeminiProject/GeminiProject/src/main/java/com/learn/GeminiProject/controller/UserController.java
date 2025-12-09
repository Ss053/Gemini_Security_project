package com.learn.GeminiProject.controller;

import com.learn.GeminiProject.DTO.UserDto;
import com.learn.GeminiProject.models.User;
import com.learn.GeminiProject.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {
    private final UserServices userServices;
    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        return userServices.createUser(user);
    }

    @PutMapping("/user")
    public ResponseEntity<UserDto> updateUser(@RequestBody User user) {
        return userServices.updateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        return userServices.deleteUser(id);
    }
}

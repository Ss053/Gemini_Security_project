package com.learn.GeminiProject.services;

import com.learn.GeminiProject.DTO.UserDto;
import com.learn.GeminiProject.models.User;
import org.springframework.http.ResponseEntity;

public interface UserServices {
        public ResponseEntity<UserDto> createUser(User user);
        public ResponseEntity<UserDto> updateUser(User user);
        public ResponseEntity<UserDto> deleteUser(Long id);
}

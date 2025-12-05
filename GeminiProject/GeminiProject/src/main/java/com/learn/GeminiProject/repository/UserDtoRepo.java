package com.learn.GeminiProject.repository;

import com.learn.GeminiProject.DTO.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDtoRepo extends JpaRepository<UserDto, Long> {
}

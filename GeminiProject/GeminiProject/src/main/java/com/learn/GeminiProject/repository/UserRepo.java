package com.learn.GeminiProject.repository;

import com.learn.GeminiProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}

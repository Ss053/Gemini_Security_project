package com.learn.GeminiProject.repository;

import com.learn.GeminiProject.models.Task;
import com.learn.GeminiProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);

}

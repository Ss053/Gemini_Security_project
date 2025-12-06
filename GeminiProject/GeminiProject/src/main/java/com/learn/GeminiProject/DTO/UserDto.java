package com.learn.GeminiProject.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String username;
    private String password;

    private String email;
}

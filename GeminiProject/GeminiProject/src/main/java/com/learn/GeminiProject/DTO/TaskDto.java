package com.learn.GeminiProject.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;

    private Boolean completed;

}

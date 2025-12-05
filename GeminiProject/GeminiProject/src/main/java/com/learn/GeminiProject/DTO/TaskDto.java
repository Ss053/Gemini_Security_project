package com.learn.GeminiProject.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class TaskDto {
    @Id
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    @Column(columnDefinition = "boolean default false")
    private Boolean completed;

}

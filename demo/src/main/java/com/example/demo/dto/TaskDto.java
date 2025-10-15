package com.example.demo.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private String status;
}

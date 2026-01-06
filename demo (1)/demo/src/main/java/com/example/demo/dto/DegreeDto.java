package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DegreeDto {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private Long departmentId;

    private Integer duration; // in years
}

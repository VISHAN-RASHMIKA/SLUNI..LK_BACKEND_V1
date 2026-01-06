package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubjectDto {
    @NotBlank
    private String name;

    private String subjectCode;

    private String description;

    @NotNull
    private Long degreeId;

    private Long departmentId;

    @NotNull
    private Integer academicYear;
}

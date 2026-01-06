package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AcademicYearDto {
    @NotNull
    private Integer year;

    @NotNull
    private Long degreeId;
}

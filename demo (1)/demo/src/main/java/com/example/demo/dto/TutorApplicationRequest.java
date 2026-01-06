package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TutorApplicationRequest {
    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String qualifications;

    @NotBlank
    private String expertiseSubjects;

    @NotNull
    private Long universityId;

    @NotNull
    private Long departmentId;

    @NotNull
    private Long degreeId;
}
package com.example.demo.dto;

import com.example.demo.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//@Data
//public class RegisterRequest {
//    @NotBlank
//    private String fullName;
//
//    @Email
//    @NotBlank
//    private String email;
//
//    @NotBlank
//    private String password;
//
//    private String role; // "STUDENT" by default
//}


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

    // Student specific fields
    private Long universityId;
    private Long departmentId;
    private Long degreeId;
    private Integer academicYear;

    // Tutor specific fields
    private String qualifications;
    private String expertiseSubjects;
}
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subjects")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "subject_code")
    private String subjectCode;

    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "academic_year")
    private Integer academicYear;

    private String description;
}
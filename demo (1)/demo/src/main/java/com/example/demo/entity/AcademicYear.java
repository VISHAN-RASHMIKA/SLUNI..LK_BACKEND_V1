package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "academic_years")
@Data
public class AcademicYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;
}
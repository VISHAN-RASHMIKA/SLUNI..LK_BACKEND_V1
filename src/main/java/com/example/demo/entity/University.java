package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "universities")
@Data
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // e.g., "University of Colombo"
}
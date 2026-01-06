package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

//@Entity
//@Table(name = "departments")
//@Data
//public class Department {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name; // e.g., "Faculty of Medicine"
//
//    @ManyToOne
//    @JoinColumn(name = "university_id")
//    private University university;
//}



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "departments")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "Faculty of Medicine"

    private String description;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
}
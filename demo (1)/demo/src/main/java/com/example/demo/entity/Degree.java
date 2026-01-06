package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

//@Entity
//@Table(name = "degrees")
//@Data
//public class Degree {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name; // e.g., "MBBS", "BSc Computer Science"
//
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
//}


@Entity
@Table(name = "degrees")
@Data
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "MBBS", "BSc Computer Science"

    private String description;

    private Integer duration; // in years

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "students")
//@Data
//@EqualsAndHashCode(callSuper = true)
//public class Student extends User {
//
//    @ManyToOne
//    @JoinColumn(name = "university_id")
//    private University university;
//
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    @ManyToOne
//    @JoinColumn(name = "degree_id")
//    private Degree degree;
//
//    @Column(name = "academic_year")
//    private Integer academicYear;
//}


@Entity
@Table(name = "students")
@Data
@EqualsAndHashCode(callSuper = true)
public class Student extends User {
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @Column(name = "academic_year")
    private Integer academicYear;

    // Additional student-specific fields can be added here
}
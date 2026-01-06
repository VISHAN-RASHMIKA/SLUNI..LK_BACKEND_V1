package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "tutors")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Tutor extends User {
//
//    @Column(columnDefinition = "TEXT")
//    private String qualifications; // e.g., "BSc in Computer Science, UoC"
//
//    @Column
//    private String expertiseSubjects; // comma separated or JSON
//
//    private boolean isVerified = false;
//
//    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
//    private Double walletBalance = 0.0;
//}
//
//
//@Entity
//@Table(name = "tutors")
//@Data
//@EqualsAndHashCode(callSuper = true)
//public class Tutor extends User {
//
//    @Column(columnDefinition = "TEXT")
//    private String qualifications;
//
//    @Column
//    private String expertiseSubjects;
//
//    private boolean isVerified = false;
//
//    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
//    private Double walletBalance = 0.0;
//}

@Entity
@Table(name = "tutors")
@Data
@EqualsAndHashCode(callSuper = true)
public class Tutor extends User {
    @Column(columnDefinition = "TEXT")
    private String qualifications;

    @Column
    private String expertiseSubjects;

    private boolean isVerified = false;

    @Column(columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
    private Double walletBalance = 0.0;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;
}
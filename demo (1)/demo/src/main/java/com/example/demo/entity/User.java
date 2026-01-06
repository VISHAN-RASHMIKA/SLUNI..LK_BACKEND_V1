package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

//@Entity
//@Table(name = "users")
//@Inheritance(strategy = InheritanceType.JOINED)
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public abstract class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private String email;
//
//    @Column(nullable = false)
//    private String password; // Will be hashed
//
//    @Column(nullable = false)
//    private String fullName;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
//
//    private boolean isActive = true;
//}



//@Entity
//@Table(name = "users")
//@Inheritance(strategy = InheritanceType.JOINED)
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = false)  // Base class doesn't extend anything else
//public abstract class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private String email;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(nullable = false)
//    private String fullName;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Role role;
//
//    private boolean isActive = true;
//}

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
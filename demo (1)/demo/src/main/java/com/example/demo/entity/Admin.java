package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "admins")
@Data
@EqualsAndHashCode(callSuper = true)  // Important! Fixes the warning
public class Admin extends User {
    // Extra fields can be added later
}
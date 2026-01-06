package com.example.demo.repository;

import com.example.demo.entity.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DegreeRepository extends JpaRepository<Degree, Long> {
    List<Degree> findByDepartmentId(Long departmentId);

    Optional<Degree> findByNameAndDepartmentId(String name, Long departmentId);
}
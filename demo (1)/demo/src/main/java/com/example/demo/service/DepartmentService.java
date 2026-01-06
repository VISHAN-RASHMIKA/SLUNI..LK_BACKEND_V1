package com.example.demo.service;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entity.Department;
import com.example.demo.entity.University;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UniversityRepository universityRepository;

    public Department createDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());

        University university = universityRepository.findById(departmentDto.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found with id: " + departmentDto.getUniversityId()));

        department.setUniversity(university);

        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    public List<Department> getDepartmentsByUniversityId(Long universityId) {
        return departmentRepository.findByUniversityId(universityId);
    }

    public Department updateDepartment(Long id, DepartmentDto departmentDto) {
        Department department = getDepartmentById(id);
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());

        if (!department.getUniversity().getId().equals(departmentDto.getUniversityId())) {
            University university = universityRepository.findById(departmentDto.getUniversityId())
                    .orElseThrow(() -> new RuntimeException("University not found with id: " + departmentDto.getUniversityId()));
            department.setUniversity(university);
        }

        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
}


package com.example.demo.service;

import com.example.demo.dto.DegreeDto;
import com.example.demo.entity.Degree;
import com.example.demo.entity.Department;
import com.example.demo.repository.DegreeRepository;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DegreeService {
    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Degree createDegree(DegreeDto degreeDto) {
        Degree degree = new Degree();
        degree.setName(degreeDto.getName());
        degree.setDescription(degreeDto.getDescription());
        degree.setDuration(degreeDto.getDuration());

        Department department = departmentRepository.findById(degreeDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + degreeDto.getDepartmentId()));

        degree.setDepartment(department);

        return degreeRepository.save(degree);
    }

    public List<Degree> getAllDegrees() {
        return degreeRepository.findAll();
    }

    public Degree getDegreeById(Long id) {
        return degreeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Degree not found with id: " + id));
    }

    public List<Degree> getDegreesByDepartmentId(Long departmentId) {
        return degreeRepository.findByDepartmentId(departmentId);
    }

    public Degree updateDegree(Long id, DegreeDto degreeDto) {
        Degree degree = getDegreeById(id);
        degree.setName(degreeDto.getName());
        degree.setDescription(degreeDto.getDescription());
        degree.setDuration(degreeDto.getDuration());

        if (!degree.getDepartment().getId().equals(degreeDto.getDepartmentId())) {
            Department department = departmentRepository.findById(degreeDto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + degreeDto.getDepartmentId()));
            degree.setDepartment(department);
        }

        return degreeRepository.save(degree);
    }

    public void deleteDegree(Long id) {
        Degree degree = getDegreeById(id);
        degreeRepository.delete(degree);
    }
}


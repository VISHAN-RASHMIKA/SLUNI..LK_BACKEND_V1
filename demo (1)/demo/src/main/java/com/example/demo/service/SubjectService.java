package com.example.demo.service;

import com.example.demo.dto.SubjectDto;
import com.example.demo.entity.Degree;
import com.example.demo.entity.Department;
import com.example.demo.entity.Subject;
import com.example.demo.repository.DegreeRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Subject createSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setSubjectCode(subjectDto.getSubjectCode());
        subject.setDescription(subjectDto.getDescription());
        subject.setAcademicYear(subjectDto.getAcademicYear());

        Degree degree = degreeRepository.findById(subjectDto.getDegreeId())
                .orElseThrow(() -> new RuntimeException("Degree not found with id: " + subjectDto.getDegreeId()));

        subject.setDegree(degree);

        if (subjectDto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(subjectDto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + subjectDto.getDepartmentId()));
            subject.setDepartment(department);
        }

        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
    }

    public List<Subject> getSubjectsByDegreeAndYear(Long degreeId, Integer academicYear) {
        return subjectRepository.findByDegreeIdAndAcademicYear(degreeId, academicYear);
    }

    public List<Subject> getSubjectsByDepartmentId(Long departmentId) {
        return subjectRepository.findByDepartmentId(departmentId);
    }

    public Subject updateSubject(Long id, SubjectDto subjectDto) {
        Subject subject = getSubjectById(id);
        subject.setName(subjectDto.getName());
        subject.setSubjectCode(subjectDto.getSubjectCode());
        subject.setDescription(subjectDto.getDescription());
        subject.setAcademicYear(subjectDto.getAcademicYear());

        if (!subject.getDegree().getId().equals(subjectDto.getDegreeId())) {
            Degree degree = degreeRepository.findById(subjectDto.getDegreeId())
                    .orElseThrow(() -> new RuntimeException("Degree not found with id: " + subjectDto.getDegreeId()));
            subject.setDegree(degree);
        }

        if (subjectDto.getDepartmentId() != null) {
            if (subject.getDepartment() == null || !subject.getDepartment().getId().equals(subjectDto.getDepartmentId())) {
                Department department = departmentRepository.findById(subjectDto.getDepartmentId())
                        .orElseThrow(() -> new RuntimeException("Department not found with id: " + subjectDto.getDepartmentId()));
                subject.setDepartment(department);
            }
        } else {
            subject.setDepartment(null);
        }

        return subjectRepository.save(subject);
    }

    public void deleteSubject(Long id) {
        Subject subject = getSubjectById(id);
        subjectRepository.delete(subject);
    }
}
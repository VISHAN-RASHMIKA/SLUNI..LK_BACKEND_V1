package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private UniversityService universityService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private AcademicYearService academicYearService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TutorService tutorService;

    // University Management
    @PostMapping("/universities")
    public ResponseEntity<University> createUniversity(@Valid @RequestBody UniversityDto universityDto) {
        University university = universityService.createUniversity(universityDto);
        return ResponseEntity.ok(university);
    }

    @GetMapping("/universities")
    public ResponseEntity<List<University>> getAllUniversities() {
        List<University> universities = universityService.getAllUniversities();
        return ResponseEntity.ok(universities);
    }

    @GetMapping("/universities/{id}")
    public ResponseEntity<University> getUniversityById(@PathVariable Long id) {
        University university = universityService.getUniversityById(id);
        return ResponseEntity.ok(university);
    }

    @PutMapping("/universities/{id}")
    public ResponseEntity<University> updateUniversity(@PathVariable Long id, @Valid @RequestBody UniversityDto universityDto) {
        University university = universityService.updateUniversity(id, universityDto);
        return ResponseEntity.ok(university);
    }

    @DeleteMapping("/universities/{id}")
    public ResponseEntity<Void> deleteUniversity(@PathVariable Long id) {
        universityService.deleteUniversity(id);
        return ResponseEntity.noContent().build();
    }

    // Department Management
    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {
        Department department = departmentService.createDepartment(departmentDto);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping("/departments/university/{universityId}")
    public ResponseEntity<List<Department>> getDepartmentsByUniversityId(@PathVariable Long universityId) {
        List<Department> departments = departmentService.getDepartmentsByUniversityId(universityId);
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentDto departmentDto) {
        Department department = departmentService.updateDepartment(id, departmentDto);
        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    // Degree Management
    @PostMapping("/degrees")
    public ResponseEntity<Degree> createDegree(@Valid @RequestBody DegreeDto degreeDto) {
        Degree degree = degreeService.createDegree(degreeDto);
        return ResponseEntity.ok(degree);
    }

    @GetMapping("/degrees")
    public ResponseEntity<List<Degree>> getAllDegrees() {
        List<Degree> degrees = degreeService.getAllDegrees();
        return ResponseEntity.ok(degrees);
    }

    @GetMapping("/degrees/{id}")
    public ResponseEntity<Degree> getDegreeById(@PathVariable Long id) {
        Degree degree = degreeService.getDegreeById(id);
        return ResponseEntity.ok(degree);
    }

    @GetMapping("/degrees/department/{departmentId}")
    public ResponseEntity<List<Degree>> getDegreesByDepartmentId(@PathVariable Long departmentId) {
        List<Degree> degrees = degreeService.getDegreesByDepartmentId(departmentId);
        return ResponseEntity.ok(degrees);
    }

    @PutMapping("/degrees/{id}")
    public ResponseEntity<Degree> updateDegree(@PathVariable Long id, @Valid @RequestBody DegreeDto degreeDto) {
        Degree degree = degreeService.updateDegree(id, degreeDto);
        return ResponseEntity.ok(degree);
    }

    @DeleteMapping("/degrees/{id}")
    public ResponseEntity<Void> deleteDegree(@PathVariable Long id) {
        degreeService.deleteDegree(id);
        return ResponseEntity.noContent().build();
    }

    // Academic Year Management
    @PostMapping("/academic-years")
    public ResponseEntity<AcademicYear> createAcademicYear(@Valid @RequestBody AcademicYearDto academicYearDto) {
        AcademicYear academicYear = academicYearService.createAcademicYear(academicYearDto);
        return ResponseEntity.ok(academicYear);
    }

    @GetMapping("/academic-years")
    public ResponseEntity<List<AcademicYear>> getAllAcademicYears() {
        List<AcademicYear> academicYears = academicYearService.getAllAcademicYears();
        return ResponseEntity.ok(academicYears);
    }

    @GetMapping("/academic-years/{id}")
    public ResponseEntity<AcademicYear> getAcademicYearById(@PathVariable Long id) {
        AcademicYear academicYear = academicYearService.getAcademicYearById(id);
        return ResponseEntity.ok(academicYear);
    }

    @GetMapping("/academic-years/degree/{degreeId}")
    public ResponseEntity<List<AcademicYear>> getAcademicYearsByDegreeId(@PathVariable Long degreeId) {
        List<AcademicYear> academicYears = academicYearService.getAcademicYearsByDegreeId(degreeId);
        return ResponseEntity.ok(academicYears);
    }

    @PutMapping("/academic-years/{id}")
    public ResponseEntity<AcademicYear> updateAcademicYear(@PathVariable Long id, @Valid @RequestBody AcademicYearDto academicYearDto) {
        AcademicYear academicYear = academicYearService.updateAcademicYear(id, academicYearDto);
        return ResponseEntity.ok(academicYear);
    }

    @DeleteMapping("/academic-years/{id}")
    public ResponseEntity<Void> deleteAcademicYear(@PathVariable Long id) {
        academicYearService.deleteAcademicYear(id);
        return ResponseEntity.noContent().build();
    }

    // Subject Management
    @PostMapping("/subjects")
    public ResponseEntity<Subject> createSubject(@Valid @RequestBody SubjectDto subjectDto) {
        Subject subject = subjectService.createSubject(subjectDto);
        return ResponseEntity.ok(subject);
    }

    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        Subject subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }

    @GetMapping("/subjects/degree/{degreeId}/year/{academicYear}")
    public ResponseEntity<List<Subject>> getSubjectsByDegreeAndYear(@PathVariable Long degreeId, @PathVariable Integer academicYear) {
        List<Subject> subjects = subjectService.getSubjectsByDegreeAndYear(degreeId, academicYear);
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/subjects/department/{departmentId}")
    public ResponseEntity<List<Subject>> getSubjectsByDepartmentId(@PathVariable Long departmentId) {
        List<Subject> subjects = subjectService.getSubjectsByDepartmentId(departmentId);
        return ResponseEntity.ok(subjects);
    }

    @PutMapping("/subjects/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @Valid @RequestBody SubjectDto subjectDto) {
        Subject subject = subjectService.updateSubject(id, subjectDto);
        return ResponseEntity.ok(subject);
    }

    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }

    // Tutor Verification
    @GetMapping("/tutors/pending")
    public ResponseEntity<List<Tutor>> getPendingTutors() {
        List<Tutor> tutors = tutorService.getPendingTutors();
        return ResponseEntity.ok(tutors);
    }

    @PostMapping("/tutors/{id}/verify")
    public ResponseEntity<Void> verifyTutor(@PathVariable Long id) {
        tutorService.verifyTutor(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tutors/{id}/reject")
    public ResponseEntity<Void> rejectTutor(@PathVariable Long id) {
        tutorService.rejectTutor(id);
        return ResponseEntity.ok().build();
    }
}

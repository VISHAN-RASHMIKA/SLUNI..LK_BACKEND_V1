package com.example.demo.service;

import com.example.demo.dto.AcademicYearDto;
import com.example.demo.entity.AcademicYear;
import com.example.demo.entity.Degree;
import com.example.demo.repository.AcademicYearRepository;
import com.example.demo.repository.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicYearService {
    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    public AcademicYear createAcademicYear(AcademicYearDto academicYearDto) {
        AcademicYear academicYear = new AcademicYear();
        academicYear.setYear(academicYearDto.getYear());

        Degree degree = degreeRepository.findById(academicYearDto.getDegreeId())
                .orElseThrow(() -> new RuntimeException("Degree not found with id: " + academicYearDto.getDegreeId()));

        academicYear.setDegree(degree);

        return academicYearRepository.save(academicYear);
    }

    public List<AcademicYear> getAllAcademicYears() {
        return academicYearRepository.findAll();
    }

    public AcademicYear getAcademicYearById(Long id) {
        return academicYearRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic Year not found with id: " + id));
    }

    public List<AcademicYear> getAcademicYearsByDegreeId(Long degreeId) {
        return academicYearRepository.findByDegreeId(degreeId);
    }

    public AcademicYear updateAcademicYear(Long id, AcademicYearDto academicYearDto) {
        AcademicYear academicYear = getAcademicYearById(id);
        academicYear.setYear(academicYearDto.getYear());

        if (!academicYear.getDegree().getId().equals(academicYearDto.getDegreeId())) {
            Degree degree = degreeRepository.findById(academicYearDto.getDegreeId())
                    .orElseThrow(() -> new RuntimeException("Degree not found with id: " + academicYearDto.getDegreeId()));
            academicYear.setDegree(degree);
        }

        return academicYearRepository.save(academicYear);
    }

    public void deleteAcademicYear(Long id) {
        AcademicYear academicYear = getAcademicYearById(id);
        academicYearRepository.delete(academicYear);
    }
}


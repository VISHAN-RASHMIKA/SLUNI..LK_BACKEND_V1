package com.example.demo.service;

import com.example.demo.dto.UniversityDto;
import com.example.demo.entity.University;
import com.example.demo.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    public University createUniversity(UniversityDto universityDto) {
        University university = new University();
        university.setName(universityDto.getName());
        university.setDescription(universityDto.getDescription());
        university.setLocation(universityDto.getLocation());

        return universityRepository.save(university);
    }

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public University getUniversityById(Long id) {
        return universityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("University not found with id: " + id));
    }

    public University updateUniversity(Long id, UniversityDto universityDto) {
        University university = getUniversityById(id);
        university.setName(universityDto.getName());
        university.setDescription(universityDto.getDescription());
        university.setLocation(universityDto.getLocation());

        return universityRepository.save(university);
    }

    public void deleteUniversity(Long id) {
        University university = getUniversityById(id);
        universityRepository.delete(university);
    }
}

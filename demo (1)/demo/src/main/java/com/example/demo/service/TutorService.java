package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.entity.Tutor;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {
    @Autowired
    private UserRepository userRepository;

    public List<Tutor> getPendingTutors() {
        return userRepository.findByRoleAndIsVerified(Role.TUTOR, false);
    }

    public void verifyTutor(Long id) {
        Tutor tutor = (Tutor) userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor not found with id: " + id));

        if (tutor.getRole() != Role.TUTOR) {
            throw new RuntimeException("User is not a tutor");
        }

        tutor.setVerified(true);
        userRepository.save(tutor);
    }

    public void rejectTutor(Long id) {
        Tutor tutor = (Tutor) userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor not found with id: " + id));

        if (tutor.getRole() != Role.TUTOR) {
            throw new RuntimeException("User is not a tutor");
        }

        userRepository.delete(tutor);
    }
}

//package com.example.demo.service;
//
//
//import com.example.demo.dto.AuthResponse;
//import com.example.demo.dto.LoginRequest;
//import com.example.demo.dto.RegisterRequest;
//import com.example.demo.dto.TutorApplicationRequest;
//import com.example.demo.entity.*;
//import com.example.demo.repository.DegreeRepository;
//import com.example.demo.repository.DepartmentRepository;
//import com.example.demo.repository.UniversityRepository;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.util.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
////
////@Service
////public class AuthService {
////
////    @Autowired
////    private UserRepository userRepository;
////
////    @Autowired
////    private PasswordEncoder passwordEncoder;
////
////    @Autowired
////    private JwtUtil jwtUtil;
////
////    public AuthResponse register(RegisterRequest request) {
////        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
////            throw new RuntimeException("Email already exists");
////        }
////
////        Student student = new Student();
////        student.setEmail(request.getEmail());
////        student.setPassword(passwordEncoder.encode(request.getPassword()));
////        student.setFullName(request.getFullName());
////        student.setRole(Role.STUDENT);
////
////        userRepository.save(student);
////
////        String token = jwtUtil.generateToken(student.getEmail(), student.getRole().name());
////
////        AuthResponse response = new AuthResponse();
////        response.setToken(token);
////        response.setRole(student.getRole().name());
////        response.setEmail(student.getEmail());
////
////        return response;
////    }
////
////    public AuthResponse login(LoginRequest request) {
////        User user = userRepository.findByEmail(request.getEmail())
////                .orElseThrow(() -> new RuntimeException("User not found"));
////
////        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
////            throw new RuntimeException("Invalid password");
////        }
////
////        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
////
////        AuthResponse response = new AuthResponse();
////        response.setToken(token);
////        response.setRole(user.getRole().name());
////        response.setEmail(user.getEmail());
////
////        return response;
////    }
////}
//
//
//@Service
//public class AuthService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UniversityRepository universityRepository;
//
//    @Autowired
//    private DepartmentRepository departmentRepository;
//
//    @Autowired
//    private DegreeRepository degreeRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public AuthResponse register(RegisterRequest request) {
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new RuntimeException("Email already exists");
//        }
//
//        User user;
//
//        if (request.getRole() == Role.STUDENT) {
//            Student student = new Student();
//            student.setEmail(request.getEmail());
//            student.setPassword(passwordEncoder.encode(request.getPassword()));
//            student.setFullName(request.getFullName());
//            student.setRole(Role.STUDENT);
//
//            // Set academic profile
//            if (request.getUniversityId() != null) {
//                student.setUniversity(universityRepository.findById(request.getUniversityId())
//                        .orElseThrow(() -> new RuntimeException("University not found")));
//            }
//
//            if (request.getDepartmentId() != null) {
//                student.setDepartment(departmentRepository.findById(request.getDepartmentId())
//                        .orElseThrow(() -> new RuntimeException("Department not found")));
//            }
//
//            if (request.getDegreeId() != null) {
//                student.setDegree(degreeRepository.findById(request.getDegreeId())
//                        .orElseThrow(() -> new RuntimeException("Degree not found")));
//            }
//
//            student.setAcademicYear(request.getAcademicYear());
//
//            user = student;
//        } else if (request.getRole() == Role.TUTOR) {
//            Tutor tutor = new Tutor();
//            tutor.setEmail(request.getEmail());
//            tutor.setPassword(passwordEncoder.encode(request.getPassword()));
//            tutor.setFullName(request.getFullName());
//            tutor.setRole(Role.TUTOR);
//            tutor.setQualifications(request.getQualifications());
//            tutor.setExpertiseSubjects(request.getExpertiseSubjects());
//            tutor.setVerified(false); // Needs admin approval
//
//            // Set academic profile
//            if (request.getUniversityId() != null) {
//                tutor.setUniversity(universityRepository.findById(request.getUniversityId())
//                        .orElseThrow(() -> new RuntimeException("University not found")));
//            }
//
//            if (request.getDepartmentId() != null) {
//                tutor.setDepartment(departmentRepository.findById(request.getDepartmentId())
//                        .orElseThrow(() -> new RuntimeException("Department not found")));
//            }
//
//            if (request.getDegreeId() != null) {
//                tutor.setDegree(degreeRepository.findById(request.getDegreeId())
//                        .orElseThrow(() -> new RuntimeException("Degree not found")));
//            }
//
//            user = tutor;
//        } else if (request.getRole() == Role.ADMIN) {
//            // Admin registration should be more restricted in production
//            Admin admin = new Admin();
//            admin.setEmail(request.getEmail());
//            admin.setPassword(passwordEncoder.encode(request.getPassword()));
//            admin.setFullName(request.getFullName());
//            admin.setRole(Role.ADMIN);
//
//            user = admin;
//        } else {
//            throw new RuntimeException("Invalid role specified");
//        }
//
//        userRepository.save(user);
//
//        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
//
//        AuthResponse response = new AuthResponse();
//        response.setToken(token);
//        response.setRole(user.getRole().name());
//        response.setEmail(user.getEmail());
//
//        return response;
//    }
//
//    public AuthResponse login(LoginRequest request) {
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        if (!user.isActive()) {
//            throw new RuntimeException("Account is deactivated");
//        }
//
//        // Check if tutor is verified
//        if (user.getRole() == Role.TUTOR) {
//            Tutor tutor = (Tutor) user;
//            if (!tutor.isVerified()) {
//                throw new RuntimeException("Tutor account is not verified yet");
//            }
//        }
//
//        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
//
//        AuthResponse response = new AuthResponse();
//        response.setToken(token);
//        response.setRole(user.getRole().name());
//        response.setEmail(user.getEmail());
//
//        return response;
//    }
//
//    public void applyToBecomeTutor(TutorApplicationRequest request) {
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new RuntimeException("Email already exists");
//        }
//
//        Tutor tutor = new Tutor();
//        tutor.setEmail(request.getEmail());
//        tutor.setPassword(passwordEncoder.encode(request.getPassword()));
//        tutor.setFullName(request.getFullName());
//        tutor.setRole(Role.TUTOR);
//        tutor.setQualifications(request.getQualifications());
//        tutor.setExpertiseSubjects(request.getExpertiseSubjects());
//        tutor.setVerified(false); // Needs admin approval
//
//        // Set academic profile
//        tutor.setUniversity(universityRepository.findById(request.getUniversityId())
//                .orElseThrow(() -> new RuntimeException("University not found")));
//
//        tutor.setDepartment(departmentRepository.findById(request.getDepartmentId())
//                .orElseThrow(() -> new RuntimeException("Department not found")));
//
//        tutor.setDegree(degreeRepository.findById(request.getDegreeId())
//                .orElseThrow(() -> new RuntimeException("Degree not found")));
//
//        userRepository.save(tutor);
//    }
//}

package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.TutorApplicationRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.DegreeRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user;

        if (request.getRole() == Role.STUDENT) {
            Student student = new Student();
            student.setEmail(request.getEmail());
            student.setPassword(passwordEncoder.encode(request.getPassword()));
            student.setFullName(request.getFullName());
            student.setRole(Role.STUDENT);

            // Set academic profile
            if (request.getUniversityId() != null) {
                student.setUniversity(universityRepository.findById(request.getUniversityId())
                        .orElseThrow(() -> new RuntimeException("University not found")));
            }

            if (request.getDepartmentId() != null) {
                student.setDepartment(departmentRepository.findById(request.getDepartmentId())
                        .orElseThrow(() -> new RuntimeException("Department not found")));
            }

            if (request.getDegreeId() != null) {
                student.setDegree(degreeRepository.findById(request.getDegreeId())
                        .orElseThrow(() -> new RuntimeException("Degree not found")));
            }

            student.setAcademicYear(request.getAcademicYear());

            user = student;
        } else if (request.getRole() == Role.TUTOR) {
            Tutor tutor = new Tutor();
            tutor.setEmail(request.getEmail());
            tutor.setPassword(passwordEncoder.encode(request.getPassword()));
            tutor.setFullName(request.getFullName());
            tutor.setRole(Role.TUTOR);
            tutor.setQualifications(request.getQualifications());
            tutor.setExpertiseSubjects(request.getExpertiseSubjects());
            tutor.setVerified(false); // Needs admin approval

            // Set academic profile
            if (request.getUniversityId() != null) {
                tutor.setUniversity(universityRepository.findById(request.getUniversityId())
                        .orElseThrow(() -> new RuntimeException("University not found")));
            }

            if (request.getDepartmentId() != null) {
                tutor.setDepartment(departmentRepository.findById(request.getDepartmentId())
                        .orElseThrow(() -> new RuntimeException("Department not found")));
            }

            if (request.getDegreeId() != null) {
                tutor.setDegree(degreeRepository.findById(request.getDegreeId())
                        .orElseThrow(() -> new RuntimeException("Degree not found")));
            }

            user = tutor;
        } else if (request.getRole() == Role.ADMIN) {
            // Admin registration should be more restricted in production
            Admin admin = new Admin();
            admin.setEmail(request.getEmail());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            admin.setFullName(request.getFullName());
            admin.setRole(Role.ADMIN);

            user = admin;
        } else {
            throw new RuntimeException("Invalid role specified");
        }

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setRole(user.getRole().name());
        response.setEmail(user.getEmail());

        return response;
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.isActive()) {
            throw new RuntimeException("Account is deactivated");
        }

        // Check if tutor is verified
        if (user.getRole() == Role.TUTOR) {
            Tutor tutor = (Tutor) user;
            if (!tutor.isVerified()) {
                throw new RuntimeException("Tutor account is not verified yet");
            }
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setRole(user.getRole().name());
        response.setEmail(user.getEmail());

        return response;
    }

    public void applyToBecomeTutor(TutorApplicationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Tutor tutor = new Tutor();
        tutor.setEmail(request.getEmail());
        tutor.setPassword(passwordEncoder.encode(request.getPassword()));
        tutor.setFullName(request.getFullName());
        tutor.setRole(Role.TUTOR);
        tutor.setQualifications(request.getQualifications());
        tutor.setExpertiseSubjects(request.getExpertiseSubjects());
        tutor.setVerified(false); // Needs admin approval

        // Set academic profile
        tutor.setUniversity(universityRepository.findById(request.getUniversityId())
                .orElseThrow(() -> new RuntimeException("University not found")));

        tutor.setDepartment(departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found")));

        tutor.setDegree(degreeRepository.findById(request.getDegreeId())
                .orElseThrow(() -> new RuntimeException("Degree not found")));

        userRepository.save(tutor);
    }
}
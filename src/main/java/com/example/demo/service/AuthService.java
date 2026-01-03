package com.example.demo.service;


import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        Student student = new Student();
        student.setEmail(request.getEmail());
        student.setPassword(passwordEncoder.encode(request.getPassword()));
        student.setFullName(request.getFullName());
        student.setRole(Role.STUDENT);

        userRepository.save(student);

        String token = jwtUtil.generateToken(student.getEmail(), student.getRole().name());

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setRole(student.getRole().name());
        response.setEmail(student.getEmail());

        return response;
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

        AuthResponse response = new AuthResponse();
        response.setToken(token);
        response.setRole(user.getRole().name());
        response.setEmail(user.getEmail());

        return response;
    }
}

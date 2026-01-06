package com.example.demo.repository;

import com.example.demo.entity.Role;
import com.example.demo.entity.Tutor;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByEmail(String email);
//}


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<Tutor> findByRoleAndIsVerified(Role role, boolean isVerified);
}
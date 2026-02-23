package com.project.feedback.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.feedback.entity.Userentity;
import com.project.feedback.repository.UserRepository;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository repo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Userentity> getAllUsers() {
        return repo.findAll();
    }

    public Userentity saveUser(Userentity user) {
        try {
            // Handle password encoding
            String rawPassword = user.getPassword() != null ? user.getPassword() : user.getPasswordHash();
            if (rawPassword == null || rawPassword.trim().isEmpty()) {
                throw new RuntimeException("Password is required");
            }
            
            user.setPasswordHash(passwordEncoder.encode(rawPassword));
            user.setCreatedDate(LocalDateTime.now());
            user.setActive(true);
            
            return repo.save(user);
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save user: " + e.getMessage());
        }
    }

    public Optional<Userentity> getUserById(Long id) {
        return repo.findById(id);
    }

    public void deleteUser(Long id) {
        repo.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return repo.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
    
    public Userentity findByUsername(String username) {
        return repo.findByUsername(username);
    }
}

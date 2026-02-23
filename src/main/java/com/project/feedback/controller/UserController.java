package com.project.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.feedback.entity.Userentity;
import com.project.feedback.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserService service;
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody Userentity user) {
        try {
            Optional<Userentity> existingUser = service.getUserById(id);
            if (existingUser.isPresent()) {
                user.setId(id);
                Userentity updatedUser = service.saveUser(user);
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Update error: ", e);
            return ResponseEntity.badRequest().body("Update failed: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            service.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            logger.error("Delete error: ", e);
            return ResponseEntity.badRequest().body("Delete failed: " + e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Userentity> getAllUsers() {
        return service.getAllUsers();
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addUser(@RequestBody Userentity user) {
        try {
            // Check if username already exists
            if (service.existsByUsername(user.getUsername())) {
                return ResponseEntity.badRequest().body("Username already exists");
            }
            
            // Check if email already exists
            if (service.existsByEmail(user.getEmail())) {
                return ResponseEntity.badRequest().body("Email already exists");
            }
            
            Userentity savedUser = service.saveUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            logger.error("Registration error: ", e);
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
    
    @PostMapping("/init-test-users")
    public ResponseEntity<?> initTestUsers() {
        try {
            // Create test faculty user
            if (!service.existsByUsername("faculty1")) {
                Userentity faculty = new Userentity();
                faculty.setUsername("faculty1");
                faculty.setEmail("faculty1@gmail.com");
                faculty.setPassword("faculty123");
                faculty.setRole("FACULTY");
                faculty.setFirstName("John");
                faculty.setLastName("Doe");
                faculty.setEmployeeId("EMP001");
                service.saveUser(faculty);
            }
            
            // Create test student user
            if (!service.existsByUsername("student1")) {
                Userentity student = new Userentity();
                student.setUsername("student1");
                student.setEmail("student1@gmail.com");
                student.setPassword("student123");
                student.setRole("STUDENT");
                student.setFirstName("Jane");
                student.setLastName("Smith");
                student.setStudentId("STU001");
                service.saveUser(student);
            }
            
            return ResponseEntity.ok("Test users created successfully");
        } catch (Exception e) {
            logger.error("Error creating test users: ", e);
            return ResponseEntity.badRequest().body("Failed to create test users: " + e.getMessage());
        }
    }
}
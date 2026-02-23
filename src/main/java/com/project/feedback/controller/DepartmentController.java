package com.project.feedback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.feedback.entity.Department;
import com.project.feedback.repository.DepartmentRepository;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {
    
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
    
    @PostMapping
    public ResponseEntity<?> createDepartment(@RequestBody Department department) {
        try {
            System.out.println("Creating department: " + department.getName());
            Department saved = departmentRepository.save(department);
            System.out.println("Department created with ID: " + saved.getId());
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        departmentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
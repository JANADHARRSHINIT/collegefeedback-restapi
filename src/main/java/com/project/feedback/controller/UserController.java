package com.project.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import com.project.feedback.entity.Userentity;
import com.project.feedback.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
     @Autowired
    UserService service;

    @GetMapping
    public List<Userentity> getAllUsers() {
        return service.getAllUsers();
    }
    
    @PostMapping
    public Userentity addUser(@RequestBody Userentity user) {
        return service.saveUser(user);
    }
}
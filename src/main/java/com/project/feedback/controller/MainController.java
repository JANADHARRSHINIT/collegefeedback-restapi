package com.project.feedback.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class MainController {
    
    @GetMapping("/")
    public String home() {
        return "College Feedback Backend Server is Running!";
    }
}

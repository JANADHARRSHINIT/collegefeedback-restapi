package com.project.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.feedback.entity.Userentity;
import com.project.feedback.service.UserService;

@RestController
public class UserController {
     @Autowired
    UserService service;

    @GetMapping("/getuser")
    public List<Userentity> getAllFeedbackdata() {
        return service.getAllFeedback();
    }
    
    @PostMapping("/adduser")
    public Userentity addFeedbackdata(@RequestBody Userentity feedback) {
        return service.saveFeedback(feedback);
    }
}

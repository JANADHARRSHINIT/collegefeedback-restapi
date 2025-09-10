package com.project.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.feedback.entity.Feedbackentity;
import com.project.feedback.service.Feedbackservice;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {
     @Autowired
    Feedbackservice service;

    @PostMapping
    public Feedbackentity submitFeedback(@RequestBody Feedbackentity feedback) {
        return service.saveFeedback(feedback);
    }

    @GetMapping
    public List<Feedbackentity> getUserFeedback(@RequestParam Long userId) {
        return service.getUserFeedback(userId);
    }

    @GetMapping("/course/{courseId}")
    public List<Feedbackentity> getCourseFeedback(@PathVariable Long courseId) {
        return service.getCourseFeedback(courseId);
    }

    @GetMapping("/faculty/{facultyId}")
    public List<Feedbackentity> getFacultyFeedback(@PathVariable Long facultyId) {
        return service.getFacultyFeedback(facultyId);
    }
    
    @PutMapping("/{id}")
    public Feedbackentity updateFeedback(@PathVariable Long id, @RequestBody Feedbackentity feedback) {
        return service.updateFeedback(id, feedback);
    }
}
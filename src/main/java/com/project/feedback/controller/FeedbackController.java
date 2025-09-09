package com.project.feedback.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.feedback.entity.Feedbackentity;
import com.project.feedback.service.Feedbackservice;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    Feedbackservice service;

    @GetMapping
    public List<Feedbackentity> getAllFeedback() {
        return service.getAllFeedback();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedbackentity> getFeedbackById(@PathVariable Long id) {
        Optional<Feedbackentity> feedback = service.getFeedbackById(id);
        return feedback.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Feedbackentity submitFeedback(@RequestBody Feedbackentity feedback) {
        return service.saveFeedback(feedback);
    }

    @PutMapping("/{id}")
    public Feedbackentity updateFeedback(@PathVariable Long id, @RequestBody Feedbackentity feedback) {
        return service.updateFeedback(id, feedback);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        service.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public List<Feedbackentity> getUserFeedback(@PathVariable Long userId) {
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
}

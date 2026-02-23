package com.project.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('STUDENT', 'FACULTY', 'ADMIN')")
    public Feedbackentity submitFeedback(@RequestBody Feedbackentity feedback) {
        return service.saveFeedback(feedback);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public List<Feedbackentity> getAllFeedback() {
        return service.getAllFeedback();
    }

    @GetMapping("/paginated")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public Page<Feedbackentity> getAllFeedbackPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllFeedbackPaginated(pageable);
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
    
    @PutMapping("/{id}")
    public Feedbackentity updateFeedback(@PathVariable Long id, @RequestBody Feedbackentity feedback) {
        return service.updateFeedback(id, feedback);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public Page<Feedbackentity> searchFeedback(
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Integer rating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
            Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.searchFeedback(studentName, courseName, rating, pageable);
    }
}
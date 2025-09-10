package com.project.feedback.controller;

import com.project.feedback.entity.Courseentity;
import com.project.feedback.entity.Feedbackentity;
import com.project.feedback.service.CourseService;
import com.project.feedback.service.Feedbackservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private Feedbackservice feedbackService;

    @GetMapping
    public List<Courseentity> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public Courseentity createCourse(@RequestBody Courseentity course) {
        return courseService.saveCourse(course);
    }

    @PutMapping("/{id}")
    public Courseentity updateCourse(@PathVariable Long id, @RequestBody Courseentity updatedCourse) {
        return courseService.updateCourse(id, updatedCourse);
    }

    @GetMapping("/{id}/feedback")
    public List<Feedbackentity> getCourseFeedback(@PathVariable Long id) {
        return feedbackService.getCourseFeedback(id);
    }
}
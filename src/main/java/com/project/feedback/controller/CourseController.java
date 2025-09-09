package com.project.feedback.controller;

import com.project.feedback.entity.Courseentity;
import com.project.feedback.entity.Feedbackentity;
import com.project.feedback.service.CourseService;
import com.project.feedback.service.Feedbackservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private Feedbackservice feedbackService;

    @GetMapping("/courses")
    
    public List<Courseentity> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/courses")
    public Courseentity createCourse(@RequestBody Courseentity course) {
        return courseService.saveCourse(course);
    }

    @PutMapping("/courses/{id}")
    public String updateCourse(@PathVariable Long id, @RequestBody Courseentity updatedCourse) {
        return courseService.updateCourse(id, updatedCourse);
    }

    @GetMapping("courses/{id}/feedback")
    public List<Feedbackentity> getCourseFeedback(@PathVariable Long id) {
        return feedbackService.getCourseFeedback(id);
    }
}

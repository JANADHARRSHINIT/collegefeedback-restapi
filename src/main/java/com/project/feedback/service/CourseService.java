package com.project.feedback.service;

import com.project.feedback.entity.Courseentity;
import com.project.feedback.repository.Courserepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private Courserepository repo;

    public Courseentity saveCourse(Courseentity course) {
        return repo.save(course);
    }

    public List<Courseentity> getAllCourses() {
        return repo.findAll();
    }

    public Optional<Courseentity> getCourseById(Long id) {
        return repo.findById(id);
    }

    public Courseentity updateCourse(Long id, Courseentity updatedCourse) {
        updatedCourse.setId(id);
        return repo.save(updatedCourse);
    }

    public void deleteCourse(Long id) {
        repo.deleteById(id);
    }
}

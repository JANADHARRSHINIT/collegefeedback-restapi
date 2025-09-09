package com.project.feedback.service;

import com.project.feedback.entity.Courseentity;
import com.project.feedback.repository.Courserepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public String updateCourse(Long id, Courseentity updatedCourse) {
        if (repo.existsById(id)) {
			repo.save(updatedCourse);
			return id + "Data updated successfully";
		} else {
			return id + "Data not found";
		}

    }
}

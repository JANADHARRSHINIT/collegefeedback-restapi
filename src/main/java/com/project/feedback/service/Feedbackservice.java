package com.project.feedback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.feedback.entity.Feedbackentity;
import com.project.feedback.repository.FeedbackRepository;

@Service
public class Feedbackservice {
     @Autowired
    private FeedbackRepository repo;
    public Feedbackentity saveFeedback(Feedbackentity feedback) {
        return repo.save(feedback);
    }

    public List<Feedbackentity> getUserFeedback(Long userId) {
        return repo.findByUserId(userId);
    }

    public List<Feedbackentity> getCourseFeedback(Long courseId) {
        return repo.findByCourseId(courseId);
    }

    public List<Feedbackentity> getFacultyFeedback(Long facultyId) {
        return repo.findByFacultyId(facultyId);
    }

    public String updateFeedback(Long id, Feedbackentity feedback) {
        if (repo.existsById(id)) {
			repo.save(feedback);
			return id + "Data updated successfully";
		} else {
			return id + "Data not found";
		}

    }
   

}

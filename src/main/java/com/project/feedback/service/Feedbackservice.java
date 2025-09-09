package com.project.feedback.service;

import java.util.List;
import java.util.Optional;

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

    public List<Feedbackentity> getAllFeedback() {
        return repo.findAll();
    }

    public Optional<Feedbackentity> getFeedbackById(Long id) {
        return repo.findById(id);
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

    public Feedbackentity updateFeedback(Long id, Feedbackentity feedback) {
        feedback.setId(id);
        return repo.save(feedback);
    }

    public void deleteFeedback(Long id) {
        repo.deleteById(id);
    }
}

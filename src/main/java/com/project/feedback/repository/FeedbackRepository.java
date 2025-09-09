package com.project.feedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.feedback.entity.Feedbackentity;

public interface FeedbackRepository extends JpaRepository<Feedbackentity,Long>{
     List<Feedbackentity> findByUserId(Long userId);

    List<Feedbackentity> findByCourseId(Long courseId);

    List<Feedbackentity> findByFacultyId(Long facultyId);
}

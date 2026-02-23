package com.project.feedback.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.feedback.entity.Feedbackentity;

public interface FeedbackRepository extends JpaRepository<Feedbackentity,Long>{
     List<Feedbackentity> findByUserId(Long userId);

    List<Feedbackentity> findByCourseId(Long courseId);

    List<Feedbackentity> findByFacultyId(Long facultyId);

    @Query("SELECT f FROM Feedbackentity f WHERE " +
           "(:studentName IS NULL OR LOWER(f.user.firstName) LIKE LOWER(CONCAT('%', :studentName, '%')) OR LOWER(f.user.lastName) LIKE LOWER(CONCAT('%', :studentName, '%'))) AND " +
           "(:courseName IS NULL OR LOWER(f.course.courseName) LIKE LOWER(CONCAT('%', :courseName, '%'))) AND " +
           "(:rating IS NULL OR f.rating = :rating)")
    Page<Feedbackentity> searchFeedback(@Param("studentName") String studentName,
                                        @Param("courseName") String courseName,
                                        @Param("rating") Integer rating,
                                        Pageable pageable);
}

package com.project.feedback.repository;

import com.project.feedback.entity.Courseentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Courserepository extends JpaRepository<Courseentity, Long> {
    Courseentity findByCourseCode(String courseCode);
    List<Courseentity> findByFacultyId(Long facultyId);
    List<Courseentity> findBySemesterAndYear(String semester, int year);
    List<Courseentity> findByIsActiveTrue();
}

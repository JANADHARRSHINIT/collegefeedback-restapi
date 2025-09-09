package com.project.feedback.repository;

import com.project.feedback.entity.Courseentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Courserepository extends JpaRepository<Courseentity, Long> {
}

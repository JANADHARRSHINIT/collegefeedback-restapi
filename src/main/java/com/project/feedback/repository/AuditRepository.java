package com.project.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.feedback.entity.Audit;
import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
    List<Audit> findByEntityTypeOrderByTimestampDesc(String entityType);
    List<Audit> findByUserIdOrderByTimestampDesc(Long userId);
}
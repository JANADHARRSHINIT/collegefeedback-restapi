package com.project.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.feedback.entity.Userentity;

@Repository
public interface UserRepository extends JpaRepository<Userentity,Long>{
    Userentity findByUsername(String username);
    Userentity findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}

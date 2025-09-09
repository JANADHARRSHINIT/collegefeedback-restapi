package com.project.feedback.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.feedback.entity.Userentity;

public interface UserRepository extends JpaRepository<Userentity,Long>{

}

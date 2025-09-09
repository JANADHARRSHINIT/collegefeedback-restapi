package com.project.feedback.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback_details")
@Data
public class Feedbackentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Long id;

    @Column(name = "feedback_type")
    private String feedbackType;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comments")
    private String comments;

    @Column(name = "is_anonymous")
    private boolean isAnonymous;

    @CreationTimestamp
    @Column(name = "submission_date", updatable = false)
    private LocalDateTime submissionDate;

    @Column(name = "semester")
    private String semester;

    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "user_id")   
    private Userentity user;

    @ManyToOne
    @JoinColumn(name = "course_id")  
    private Courseentity course;

    @ManyToOne
    @JoinColumn(name = "faculty_id") 
    private Userentity faculty;
}

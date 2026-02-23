package com.project.feedback.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "courses_details")
@Data
public class Courseentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "semester")
    private String semester;

    @Column(name = "year")
    private int year;

    @Column(name = "credits")
    private int credits;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "faculty_id")   
    private Userentity faculty;
    
    @ManyToOne
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private Department department;

    @OneToMany(mappedBy = "course")  
    private List<Feedbackentity> feedbacks;
}

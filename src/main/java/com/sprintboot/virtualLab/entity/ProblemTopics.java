package com.sprintboot.virtualLab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "problem_topics")
public class ProblemTopics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseTitle;

    @Column(length = 1000)
    private String courseDescription;

    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", unique = true)
    @JsonIgnore
    private Course course;

    // Constructors
    public ProblemTopics() {}

    public ProblemTopics(Long id, String courseTitle, String courseDescription, Course course) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.course = course;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public String getCourseDescription() { return courseDescription; }
    public void setCourseDescription(String courseDescription) { this.courseDescription = courseDescription; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}

package com.sprintboot.virtualLab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String courseName;

    @Column(length = 1000)
    private String courseDescription;

    private int courseNumbers;
    private String difficulties;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private ProblemTopics problemTopics;

    // Constructors
    public Course() {}

    public Course(Long id, String courseName, String courseDescription, int courseNumbers, String difficulties, ProblemTopics problemTopics) {
        this.id = id;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseNumbers = courseNumbers;
        this.difficulties = difficulties;
        this.problemTopics = problemTopics;
    }


    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseDescription() { return courseDescription; }
    public void setCourseDescription(String courseDescription) { this.courseDescription = courseDescription; }

    public int getCourseNumbers() { return courseNumbers; }
    public void setCourseNumbers(int courseNumbers) { this.courseNumbers = courseNumbers; }

    public String getDifficulties() { return difficulties; }
    public void setDifficulties(String difficulties) { this.difficulties = difficulties; }

    public ProblemTopics getProblemTopics() { return problemTopics; }

    public void setProblemTopics(ProblemTopics problemTopics) {
        this.problemTopics = problemTopics;
        if (problemTopics != null) {
            problemTopics.setCourse(this);
        }
    }
}
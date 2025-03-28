package com.sprintboot.virtualLab.dto;

import com.sprintboot.virtualLab.entity.ProblemTopics;

public class CourseDto {
    private Long id;
    private String courseName;
    private String courseDescription;
    private int courseNumbers; // ✅ Kept as int
    private String difficulties;
    private ProblemTopics problemTopics;

    // Constructors
    public CourseDto() {}

    public CourseDto(Long id, String courseName, String courseDescription, int courseNumbers, String difficulties, ProblemTopics problemTopics) {
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
    public void setProblemTopics(ProblemTopics problemTopicsId) { this.problemTopics = problemTopicsId; }
}
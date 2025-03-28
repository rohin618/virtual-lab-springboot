package com.sprintboot.virtualLab.dto;

public class ProblemTopicsDto {

    private Long id;
    private String courseTitle;
    private String courseDescription;
    private Long courseId;

    // Constructors
    public ProblemTopicsDto() {}

    public ProblemTopicsDto(Long id, String courseTitle, String courseDescription, Long courseId) {
        this.id = id;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseId = courseId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public String getCourseDescription() { return courseDescription; }
    public void setCourseDescription(String courseDescription) { this.courseDescription = courseDescription; }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
}

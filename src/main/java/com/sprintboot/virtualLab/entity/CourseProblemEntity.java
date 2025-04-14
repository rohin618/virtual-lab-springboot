package com.sprintboot.virtualLab.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_problem")
public class CourseProblemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userCourseStr;

    private Long problemId;

    @Column(columnDefinition = "TEXT")
    private String currentCode;

    @Column(columnDefinition = "TEXT")
    private String testcase;

    public CourseProblemEntity() {}

    public CourseProblemEntity(String userCourseStr, Long problemId, String currentCode, String testcase) {
        this.userCourseStr = userCourseStr;
        this.problemId = problemId;
        this.currentCode = currentCode;
        this.testcase = testcase;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserCourseStr() { return userCourseStr; }
    public void setUserCourseStr(String userCourseStr) { this.userCourseStr = userCourseStr; }

    public Long getProblemId() { return problemId; }
    public void setProblemId(Long problemId) { this.problemId = problemId; }

    public String getCurrentCode() { return currentCode; }
    public void setCurrentCode(String currentCode) { this.currentCode = currentCode; }

    public String getTestcase() { return testcase; }
    public void setTestcase(String testcase) { this.testcase = testcase; }
}

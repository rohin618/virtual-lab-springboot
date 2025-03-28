package com.sprintboot.virtualLab.dto;

public class ProblemSetsDto {

    private Long id;
    private String problems;
    private String difficulties;
    private Long problemTopicsId;

    // Constructors
    public ProblemSetsDto() {
    }

    public ProblemSetsDto(Long id, String problems, String difficulties, Long problemTopicsId) {
        this.id = id;
        this.problems = problems;
        this.difficulties = difficulties;
        this.problemTopicsId = problemTopicsId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(String difficulties) {
        this.difficulties = difficulties;
    }

    public Long getProblemTopicsId() {
        return problemTopicsId;
    }

    public void setProblemTopicsId(Long problemTopicsId) {
        this.problemTopicsId = problemTopicsId;
    }
}

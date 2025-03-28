package com.sprintboot.virtualLab.dto;

public class ProblemTopicOverviewDto {

    private Long id;
    private String problemOverviewTitle;
    private String problemOverviewDes;
    private Long problemTopicsId;

    // Constructors
    public ProblemTopicOverviewDto() {
    }

    public ProblemTopicOverviewDto(Long id, String problemOverviewTitle, String problemOverviewDes, Long problemTopicsId) {
        this.id = id;
        this.problemOverviewTitle = problemOverviewTitle;
        this.problemOverviewDes = problemOverviewDes;
        this.problemTopicsId = problemTopicsId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProblemOverviewTitle() {
        return problemOverviewTitle;
    }

    public void setProblemOverviewTitle(String problemOverviewTitle) {
        this.problemOverviewTitle = problemOverviewTitle;
    }

    public String getProblemOverviewDes() {
        return problemOverviewDes;
    }

    public void setProblemOverviewDes(String problemOverviewDes) {
        this.problemOverviewDes = problemOverviewDes;
    }

    public Long getProblemTopicsId() {
        return problemTopicsId;
    }

    public void setProblemTopicsId(Long problemTopicsId) {
        this.problemTopicsId = problemTopicsId;
    }
}

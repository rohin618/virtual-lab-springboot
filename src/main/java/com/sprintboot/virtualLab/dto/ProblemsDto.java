package com.sprintboot.virtualLab.dto;

public class ProblemsDto {

    private Long id;
    private String problemTitle;
    private String problemDescription;
    private String test1;
    private String output1;
    private String test2;
    private String output2;
    private Long problemSetId;

    // Constructors
    public ProblemsDto() {
    }

    public ProblemsDto(Long id, String problemTitle, String problemDescription, String test1, String output1, String test2, String output2, Long problemSetId) {
        this.id = id;
        this.problemTitle = problemTitle;
        this.problemDescription = problemDescription;
        this.test1 = test1;
        this.output1 = output1;
        this.test2 = test2;
        this.output2 = output2;
        this.problemSetId = problemSetId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProblemTitle() {
        return problemTitle;
    }

    public void setProblemTitle(String problemTitle) {
        this.problemTitle = problemTitle;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public String getOutput1() {
        return output1;
    }

    public void setOutput1(String output1) {
        this.output1 = output1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public String getOutput2() {
        return output2;
    }

    public void setOutput2(String output2) {
        this.output2 = output2;
    }

    public Long getProblemSetId() {
        return problemSetId;
    }

    public void setProblemSetId(Long problemSetId) {
        this.problemSetId = problemSetId;
    }
}

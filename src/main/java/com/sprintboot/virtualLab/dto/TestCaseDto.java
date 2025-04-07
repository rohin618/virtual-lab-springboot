package com.sprintboot.virtualLab.dto;

public class TestCaseDto {
    private Long id;
    private String input;
    private String expectedOutput;
    private Long problemId;

    // Default Constructor
    public TestCaseDto() {
    }

    // Constructor with parameters
    public TestCaseDto(Long id, String input, String expectedOutput, Long problemId) {
        this.id = id;
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.problemId = problemId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }
}

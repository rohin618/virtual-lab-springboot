package com.sprintboot.virtualLab.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "test_cases")
public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String input;

    @Column(nullable = false, length = 1000)
    private String expectedOutput;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problems problems; // Correct field name

    // Default Constructor (Required by JPA)
    public TestCase() {
    }

    // Constructor
    public TestCase(String input, String expectedOutput, Problems problems) {
        this.input = input;
        this.expectedOutput = expectedOutput;
        this.problems = problems;
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

    public Problems getProblems() {
        return problems;
    }

    public void setProblems(Problems problems) {
        this.problems = problems;
    }
}

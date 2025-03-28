package com.sprintboot.virtualLab.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "problem_sets")
public class ProblemSets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String problems;

    private String difficulties;

    @ManyToOne
    @JoinColumn(name = "problem_topics_id", referencedColumnName = "id")
    private ProblemTopics problemTopics;

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

    public ProblemTopics getProblemTopics() {
        return problemTopics;
    }

    public void setProblemTopics(ProblemTopics problemTopics) {
        this.problemTopics = problemTopics;
    }
}

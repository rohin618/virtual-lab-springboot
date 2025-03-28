package com.sprintboot.virtualLab.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "problem_topic_overview")
public class ProblemTopicOverview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String problemOverviewTitle;

    @Column(length = 1000)
    private String problemOverviewDes;

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

    public ProblemTopics getProblemTopics() {
        return problemTopics;
    }

    public void setProblemTopics(ProblemTopics problemTopics) {
        this.problemTopics = problemTopics;
    }
}

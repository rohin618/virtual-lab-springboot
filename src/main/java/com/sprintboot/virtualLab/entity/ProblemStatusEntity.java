package com.sprintboot.virtualLab.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "problem_status")
public class ProblemStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private String problemOverIdUserId;

    public ProblemStatusEntity() {
    }

    public ProblemStatusEntity(String status, String problemOverIdUserId) {
        this.status = status;
        this.problemOverIdUserId = problemOverIdUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProblemOverIdUserId() {
        return problemOverIdUserId;
    }

    public void setProblemOverIdUserId(String problemOverIdUserId) {
        this.problemOverIdUserId = problemOverIdUserId;
    }
}

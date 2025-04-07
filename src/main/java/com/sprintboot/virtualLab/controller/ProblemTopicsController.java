package com.sprintboot.virtualLab.controller;

import com.sprintboot.virtualLab.dto.ProblemTopicsDto;
import com.sprintboot.virtualLab.entity.ProblemTopics;
import com.sprintboot.virtualLab.service.ProblemTopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problemTopics")
public class ProblemTopicsController {

    private final ProblemTopicsService problemTopicsService;

    public ProblemTopicsController(ProblemTopicsService problemTopicsService) {
        this.problemTopicsService = problemTopicsService;
    }

    @PostMapping
    public ResponseEntity<?> createProblemTopic(@RequestBody ProblemTopicsDto problemTopicsDto) {
        try {
            ProblemTopicsDto createdProblemTopic = problemTopicsService.createProblemTopic(problemTopicsDto);
            return ResponseEntity.ok(createdProblemTopic);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating Problem Topic: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSeparateProblemTopic(@PathVariable Long id) {
        try {
            ProblemTopics problemTopics = problemTopicsService.getProblemTopicByCourseId(id);
            return ResponseEntity.ok(problemTopics);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("ProblemTopic not yet added for Course ID: " + id);
        }
    }

}

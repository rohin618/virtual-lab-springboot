package com.sprintboot.virtualLab.controller;


import com.sprintboot.virtualLab.dto.ProblemTopicOverviewDto;
import com.sprintboot.virtualLab.entity.ProblemTopicOverview;
import com.sprintboot.virtualLab.service.ProblemTopicsOverviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problemTopicsOverview")
public class ProblemTopicOverviewController {

    private final ProblemTopicsOverviewService problemTopicsOverviewService;

    public ProblemTopicOverviewController(ProblemTopicsOverviewService problemTopicsOverviewService) {
        this.problemTopicsOverviewService = problemTopicsOverviewService;
    }

    @PostMapping
    public ResponseEntity<ProblemTopicOverviewDto> createProblemTopicOverview(@RequestBody ProblemTopicOverviewDto problemTopicOverviewDto){
    return ResponseEntity.ok(problemTopicsOverviewService.create(problemTopicOverviewDto));
    }

    @GetMapping
    public  ResponseEntity<List<ProblemTopicOverview>> getProblemTopicOverview(){
    return ResponseEntity.ok(problemTopicsOverviewService.getAll());
    }
}

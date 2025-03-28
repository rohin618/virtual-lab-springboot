package com.sprintboot.virtualLab.controller;


import com.sprintboot.virtualLab.dto.ProblemSetsDto;
import com.sprintboot.virtualLab.service.ProblemSetsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/problemSets")
public class ProblemSetsController {


    private final ProblemSetsService problemSetsService;

    public ProblemSetsController(ProblemSetsService problemSetsService) {
        this.problemSetsService = problemSetsService;
    }

    @PostMapping
    public ResponseEntity<?> createProblemSets(ProblemSetsDto problemSetsDto){

    }
}

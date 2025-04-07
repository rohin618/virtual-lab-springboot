package com.sprintboot.virtualLab.controller;


import com.sprintboot.virtualLab.dto.ProblemSetsDto;
import com.sprintboot.virtualLab.entity.ProblemSets;
import com.sprintboot.virtualLab.service.ProblemSetsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problemSets")
public class ProblemSetsController {


    private final ProblemSetsService problemSetsService;

    public ProblemSetsController(ProblemSetsService problemSetsService) {
        this.problemSetsService = problemSetsService;
    }

    @PostMapping
    public ResponseEntity<?> createProblemSets(@RequestBody  ProblemSetsDto problemSetsDto){
        return ResponseEntity.ok(problemSetsService.create(problemSetsDto));
    }

    @GetMapping
    public ResponseEntity<?> getAllProblemSets(){
        return ResponseEntity.ok(problemSetsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllByProblemTopicId (@PathVariable Long id){
        List<ProblemSets> problemSets = problemSetsService.getAllByProblemTopicId(id);
        if (problemSets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No problemSet found for ProblemTopic ID: " + id);
        }

        return ResponseEntity.ok(problemSets);
    }


}

package com.sprintboot.virtualLab.controller;


import com.sprintboot.virtualLab.dto.ProblemsDto;
import com.sprintboot.virtualLab.entity.ProblemSets;
import com.sprintboot.virtualLab.entity.Problems;
import com.sprintboot.virtualLab.service.ProblemService;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problem")
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProblemsDto problemsDto){
        return ResponseEntity.ok(problemService.createProblem(problemsDto));
    }
    @GetMapping
    public ResponseEntity<List<Problems>> getAll() {
        return ResponseEntity.ok(problemService.getAllProblem());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){

        List<Problems> problems = problemService.getSingleProblem(id);
        if (problems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No problemSet found for ProblemTopic ID: " + id);
        }

        return ResponseEntity.ok(problems.get(0));
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProblemsDto problemsDto){
        return ResponseEntity.ok(problemService.updateProblem(problemsDto));
    }
}

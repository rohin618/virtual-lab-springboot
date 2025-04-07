package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.ProblemsDto;
import com.sprintboot.virtualLab.entity.Problems;

import java.util.List;

public interface ProblemInterface {
    ProblemsDto createProblem(ProblemsDto problemsDto);
    List<Problems> getAllProblem();
    String deleteProblem(Long id);
    ProblemsDto updateProblem(ProblemsDto problemsDto);
    List<Problems> getSingleProblem(Long id);
}

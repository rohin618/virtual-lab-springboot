package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.ProblemsDto;
import com.sprintboot.virtualLab.entity.ProblemSets;
import com.sprintboot.virtualLab.entity.Problems;
import com.sprintboot.virtualLab.mapper.ProblemMapper;
import com.sprintboot.virtualLab.repository.ProblemSetsRepository;
import com.sprintboot.virtualLab.repository.ProblemsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService implements ProblemInterface {

    private final ProblemsRepository problemsRepository;
    private final ProblemSetsRepository problemSetsRepository;

    public ProblemService(ProblemsRepository problemsRepository, ProblemSetsRepository problemSetsRepository) {
        this.problemsRepository = problemsRepository;
        this.problemSetsRepository = problemSetsRepository;
    }

    @Override
    public ProblemsDto createProblem(ProblemsDto problemsDto) {
        Optional<ProblemSets> problemSets = problemSetsRepository.findById(problemsDto.getProblemSetId());

        return problemSets.map(set ->
                ProblemMapper.mapToDto(problemsRepository.save(ProblemMapper.mapToEntity(problemsDto, set)))
        ).orElse(null);
    }

    @Override
    public List<Problems> getAllProblem() {
        return problemsRepository.findAll();
    }

    /**
     * Update an existing problem
     */
    @Override
    @Transactional
    public ProblemsDto updateProblem(ProblemsDto problemsDto) {
        // Find existing problem
        Problems existingProblem = problemsRepository.findById(problemsDto.getId())
                .orElseThrow(() -> new RuntimeException("Problem not found with id: " + problemsDto.getId()));

        // Update fields
        existingProblem.setProblemTitle(problemsDto.getProblemTitle());
        existingProblem.setProblemDescription(problemsDto.getProblemDescription());
        existingProblem.setTest1(problemsDto.getTest1());
        existingProblem.setOutput1(problemsDto.getOutput1());
        existingProblem.setTest2(problemsDto.getTest2());
        existingProblem.setOutput2(problemsDto.getOutput2());

        // Update problemSets association if needed
        if (problemsDto.getProblemSetId() != null) {
            Optional<ProblemSets> optionalProblemSets = problemSetsRepository.findById(problemsDto.getProblemSetId());
            existingProblem.setProblemSets(optionalProblemSets.orElse(null));
        }

        // Save and return updated problem
        Problems updatedProblem = problemsRepository.save(existingProblem);
        return ProblemMapper.mapToDto(updatedProblem);
    }

    /**
     * Delete a problem by ID
     */
    @Override
    @Transactional
    public String deleteProblem(Long id) {
        // Check if the problem exists
        Problems problem = problemsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Problem not found with id: " + id));

        // Delete the problem
        problemsRepository.delete(problem);
        return "Problem deleted successfully.";
    }
}

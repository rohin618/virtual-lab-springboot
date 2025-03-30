package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.ProblemTopicOverviewDto;
import com.sprintboot.virtualLab.entity.ProblemTopicOverview;
import com.sprintboot.virtualLab.entity.ProblemTopics;
import com.sprintboot.virtualLab.mapper.ProblemTopicOverviewMapper;
import com.sprintboot.virtualLab.repository.ProblemTopicOverviewRepository;
import com.sprintboot.virtualLab.repository.ProblemTopicsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemTopicsOverviewService implements ProblemTopicsOverviewInterface {

    private final ProblemTopicOverviewRepository problemTopicOverviewRepository;
    private final ProblemTopicsRepository problemTopicsRepository;

    public ProblemTopicsOverviewService(ProblemTopicOverviewRepository problemTopicOverviewRepository, ProblemTopicsRepository problemTopicsRepository) {
        this.problemTopicOverviewRepository = problemTopicOverviewRepository;
        this.problemTopicsRepository = problemTopicsRepository;
    }

    @Override
    public ProblemTopicOverviewDto create(ProblemTopicOverviewDto problemTopicOverviewDto) {
        Optional<ProblemTopics> optProblemTopic = problemTopicsRepository.findById(problemTopicOverviewDto.getProblemTopicsId());
        ProblemTopics problemTopics = optProblemTopic.orElse(null);

        ProblemTopicOverview problemTopicOverview = problemTopicOverviewRepository.save(
                ProblemTopicOverviewMapper.mapToEntity(problemTopicOverviewDto, problemTopics));

        return ProblemTopicOverviewMapper.mapToDto(problemTopicOverview);
    }

    @Override
    public List<ProblemTopicOverview> getAll() {
        return problemTopicOverviewRepository.findAll();
    }


    @Override
    @Transactional
    public ProblemTopicOverviewDto update(ProblemTopicOverviewDto problemTopicOverviewDto) {
        // Check if the ProblemTopicOverview exists
        ProblemTopicOverview existingOverview = problemTopicOverviewRepository.findById(problemTopicOverviewDto.getId())
                .orElseThrow(() -> new RuntimeException("ProblemTopicOverview not found with id: " + problemTopicOverviewDto.getId()));

        // Update fields
        existingOverview.setProblemOverviewTitle(problemTopicOverviewDto.getProblemOverviewTitle());
        existingOverview.setProblemOverviewDes(problemTopicOverviewDto.getProblemOverviewDes());

        // Update problemTopics association if needed
        if (problemTopicOverviewDto.getProblemTopicsId() != null) {
            Optional<ProblemTopics> optionalProblemTopics = problemTopicsRepository.findById(problemTopicOverviewDto.getProblemTopicsId());
            existingOverview.setProblemTopics(optionalProblemTopics.orElse(null));
        }

        // Save the updated entity
        ProblemTopicOverview updatedOverview = problemTopicOverviewRepository.save(existingOverview);
        return ProblemTopicOverviewMapper.mapToDto(updatedOverview);
    }

    @Override
    @Transactional
    public String delete(Long id) {
        // Check if the ProblemTopicOverview exists
        ProblemTopicOverview problemTopicOverview = problemTopicOverviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProblemTopicOverview not found with id: " + id));

        // Delete the entity
        problemTopicOverviewRepository.delete(problemTopicOverview);
        return "ProblemTopicOverview deleted successfully.";
    }
}

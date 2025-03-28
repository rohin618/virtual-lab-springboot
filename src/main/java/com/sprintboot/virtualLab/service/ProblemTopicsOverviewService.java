package com.sprintboot.virtualLab.service;


import com.sprintboot.virtualLab.dto.ProblemTopicOverviewDto;
import com.sprintboot.virtualLab.dto.ProblemTopicsDto;
import com.sprintboot.virtualLab.entity.ProblemTopicOverview;
import com.sprintboot.virtualLab.entity.ProblemTopics;
import com.sprintboot.virtualLab.mapper.ProblemTopicOverviewMapper;
import com.sprintboot.virtualLab.repository.ProblemTopicOverviewRepository;
import com.sprintboot.virtualLab.repository.ProblemTopicsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemTopicsOverviewService implements ProblemTopicsOverviewInterface{

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

        ProblemTopicOverview problemTopicOverview = problemTopicOverviewRepository.save(ProblemTopicOverviewMapper.mapToEntity(problemTopicOverviewDto,problemTopics));

        return ProblemTopicOverviewMapper.mapToDto(problemTopicOverview);
    }

    @Override
    public List<ProblemTopicOverview> getAll() {


        return  problemTopicOverviewRepository.findAll();
    }
}

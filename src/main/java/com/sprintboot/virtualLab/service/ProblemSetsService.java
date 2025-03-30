package com.sprintboot.virtualLab.service;


import com.sprintboot.virtualLab.dto.ProblemSetsDto;
import com.sprintboot.virtualLab.entity.ProblemSets;
import com.sprintboot.virtualLab.entity.ProblemTopicOverview;
import com.sprintboot.virtualLab.entity.ProblemTopics;
import com.sprintboot.virtualLab.mapper.ProblemSetsMapper;
import com.sprintboot.virtualLab.repository.ProblemSetsRepository;
import com.sprintboot.virtualLab.repository.ProblemTopicsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemSetsService implements ProblemSetsInterface{

    private final ProblemSetsRepository problemSetsRepository;
    private final ProblemTopicsRepository problemTopicsRepository;

    public ProblemSetsService(ProblemSetsRepository problemSetsRepository, ProblemTopicsRepository problemTopicsRepository) {
        this.problemSetsRepository = problemSetsRepository;
        this.problemTopicsRepository = problemTopicsRepository;
    }


    @Override
    public ProblemSetsDto create(ProblemSetsDto problemSetsDto) {
        Optional<ProblemTopics> problemTopicsOptional = problemTopicsRepository.findById(problemSetsDto.getProblemTopicsId());
        ProblemSetsDto problemSetsDto1 = null;
        if(problemTopicsOptional.isPresent()){
            problemSetsDto1 = ProblemSetsMapper.mapToDto(problemSetsRepository.save(ProblemSetsMapper.mapToEntity(problemSetsDto,problemTopicsOptional.get())));
        }

        return problemSetsDto1;
    }

    @Override
    public List<ProblemSets> getAll() {
        return problemSetsRepository.findAll();
    }
}

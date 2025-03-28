package com.sprintboot.virtualLab.mapper;

import com.sprintboot.virtualLab.dto.ProblemSetsDto;
import com.sprintboot.virtualLab.entity.ProblemSets;
import com.sprintboot.virtualLab.entity.ProblemTopics;

public class ProblemSetsMapper {

    public static ProblemSets mapToEntity(ProblemSetsDto dto, ProblemTopics problemTopics) {
        ProblemSets entity = new ProblemSets();
        entity.setId(dto.getId());
        entity.setProblems(dto.getProblems());
        entity.setDifficulties(dto.getDifficulties());
        entity.setProblemTopics(problemTopics);
        return entity;
    }

    public static ProblemSetsDto mapToDto(ProblemSets entity) {
        return new ProblemSetsDto(
                entity.getId(),
                entity.getProblems(),
                entity.getDifficulties(),
                entity.getProblemTopics() != null ? entity.getProblemTopics().getId() : null
        );
    }
}

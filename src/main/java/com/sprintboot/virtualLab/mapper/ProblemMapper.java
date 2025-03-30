package com.sprintboot.virtualLab.mapper;

import com.sprintboot.virtualLab.dto.ProblemsDto;
import com.sprintboot.virtualLab.entity.Problems;
import com.sprintboot.virtualLab.entity.ProblemSets;

public class ProblemMapper {

    public static Problems mapToEntity(ProblemsDto dto, ProblemSets problemSets) {
        Problems entity = new Problems();
        entity.setId(dto.getId());
        entity.setProblemTitle(dto.getProblemTitle());
        entity.setProblemDescription(dto.getProblemDescription());
        entity.setTest1(dto.getTest1());
        entity.setOutput1(dto.getOutput1());
        entity.setTest2(dto.getTest2());
        entity.setOutput2(dto.getOutput2());
        entity.setProblemSets(problemSets);
        return entity;
    }

    public static ProblemsDto mapToDto(Problems entity) {
        return new ProblemsDto(
                entity.getId(),
                entity.getProblemTitle(),
                entity.getProblemDescription(),
                entity.getTest1(),
                entity.getOutput1(),
                entity.getTest2(),
                entity.getOutput2(),
                entity.getProblemSets() != null ? entity.getProblemSets().getId() : null
        );
    }
}

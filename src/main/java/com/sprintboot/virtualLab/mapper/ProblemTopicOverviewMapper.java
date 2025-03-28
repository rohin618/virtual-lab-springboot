package com.sprintboot.virtualLab.mapper;


import com.sprintboot.virtualLab.dto.ProblemTopicOverviewDto;
import com.sprintboot.virtualLab.entity.ProblemTopicOverview;
import com.sprintboot.virtualLab.entity.ProblemTopics;

public class ProblemTopicOverviewMapper {

    public static ProblemTopicOverview mapToEntity(ProblemTopicOverviewDto dto, ProblemTopics problemTopics) {
        ProblemTopicOverview entity = new ProblemTopicOverview();
        entity.setId(dto.getId());
        entity.setProblemOverviewTitle(dto.getProblemOverviewTitle());
        entity.setProblemOverviewDes(dto.getProblemOverviewDes());
        entity.setProblemTopics(problemTopics);
        return entity;
    }

    public static ProblemTopicOverviewDto mapToDto(ProblemTopicOverview entity) {
        return new ProblemTopicOverviewDto(
                entity.getId(),
                entity.getProblemOverviewTitle(),
                entity.getProblemOverviewDes(),
                entity.getProblemTopics() != null ? entity.getProblemTopics().getId() : null
        );
    }
}

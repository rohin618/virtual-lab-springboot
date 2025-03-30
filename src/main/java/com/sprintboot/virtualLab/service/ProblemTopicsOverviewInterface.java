package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.ProblemTopicOverviewDto;
import com.sprintboot.virtualLab.entity.ProblemTopicOverview;

import java.util.List;

public interface ProblemTopicsOverviewInterface {

      ProblemTopicOverviewDto create(ProblemTopicOverviewDto problemTopicOverviewDto);
      List<ProblemTopicOverview> getAll();
      ProblemTopicOverviewDto update(ProblemTopicOverviewDto problemTopicOverviewDto);
      String delete(Long id);

}

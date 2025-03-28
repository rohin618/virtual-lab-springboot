package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.ProblemSetsDto;
import com.sprintboot.virtualLab.entity.ProblemSets;

import java.util.List;

public interface ProblemSetsInterface {

    ProblemSetsDto create(ProblemSetsDto problemSetsDto);
    List<ProblemSets> getAll();
}

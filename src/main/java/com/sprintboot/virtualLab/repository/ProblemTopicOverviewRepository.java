package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.ProblemTopicOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemTopicOverviewRepository extends JpaRepository<ProblemTopicOverview, Long> {
    void deleteAllByProblemTopicsId(Long problemTopicsId);
    List<ProblemTopicOverview> findAllByProblemTopicsId(Long problemTopicsId);
}

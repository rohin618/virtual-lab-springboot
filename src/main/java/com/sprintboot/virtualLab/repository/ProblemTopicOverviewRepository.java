package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.ProblemTopicOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemTopicOverviewRepository extends JpaRepository<ProblemTopicOverview,Long> {
}

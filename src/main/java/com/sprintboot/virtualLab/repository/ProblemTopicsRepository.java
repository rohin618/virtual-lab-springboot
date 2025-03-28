package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.ProblemTopics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemTopicsRepository extends JpaRepository<ProblemTopics,Long> {
}

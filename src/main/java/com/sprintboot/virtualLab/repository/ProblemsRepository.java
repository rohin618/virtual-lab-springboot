package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.Problems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemsRepository extends JpaRepository<Problems, Long> {
    void deleteAllByProblemSetsId(Long problemSetsId);
}

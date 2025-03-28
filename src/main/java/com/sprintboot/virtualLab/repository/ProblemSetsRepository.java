package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.ProblemSets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProblemSetsRepository extends JpaRepository<ProblemSets,Long> {
}

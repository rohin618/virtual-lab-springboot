package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.ProblemSets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProblemSetsRepository extends JpaRepository<ProblemSets, Long> {
    List<ProblemSets> findAllByProblemTopicsId(Long problemTopicsId);
    void deleteAllByProblemTopicsId(Long problemTopicsId);
}

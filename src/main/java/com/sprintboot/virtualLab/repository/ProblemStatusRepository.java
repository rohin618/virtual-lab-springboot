package com.sprintboot.virtualLab.repository;


import com.sprintboot.virtualLab.entity.ProblemStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProblemStatusRepository extends JpaRepository<ProblemStatusEntity,Long> {

    Optional<ProblemStatusEntity> findByProblemOverIdUserId(String problemOverIdUserId);

}

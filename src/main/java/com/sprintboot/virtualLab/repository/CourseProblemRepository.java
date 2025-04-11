package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.CourseProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseProblemRepository extends JpaRepository<CourseProblemEntity,Long> {
}

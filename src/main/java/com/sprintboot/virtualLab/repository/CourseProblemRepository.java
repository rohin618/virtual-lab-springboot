package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.CourseProblemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseProblemRepository extends JpaRepository<CourseProblemEntity, Long> {
    Optional<CourseProblemEntity> findByUserCourseStr(String userCourseStr);

}

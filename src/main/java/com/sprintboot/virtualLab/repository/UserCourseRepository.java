package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.UserCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCourseRepository extends JpaRepository<UserCourseEntity,Long> {
    List<UserCourseEntity> findAllByUserId(Long userId);
}

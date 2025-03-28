package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.Problems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemsRepository extends JpaRepository<Problems,Long> {
}

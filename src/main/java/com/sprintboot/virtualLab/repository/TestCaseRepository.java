package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestCaseRepository  extends JpaRepository<TestCase,Long> {
    List<TestCase> findAllByProblems_Id(Long problemId);
}

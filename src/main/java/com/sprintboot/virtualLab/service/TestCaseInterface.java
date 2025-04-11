package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.TestCaseDto;
import com.sprintboot.virtualLab.entity.TestCase;

import java.util.List;

public interface TestCaseInterface {
    TestCaseDto createTestCase(TestCaseDto testCaseDto);
    List<TestCase> getAllById(Long id);
    String deleteById(Long id);
}

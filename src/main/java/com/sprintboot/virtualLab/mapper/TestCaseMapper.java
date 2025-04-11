package com.sprintboot.virtualLab.mapper;

import com.sprintboot.virtualLab.dto.TestCaseDto;
import com.sprintboot.virtualLab.entity.Problems;
import com.sprintboot.virtualLab.entity.TestCase;

public class TestCaseMapper {

    public static TestCase mapToTestCaseEntity(TestCaseDto dto, Problems problems) {
        return new TestCase(
                dto.getInput(),
                dto.getExpectedOutput(),
                problems
        );
    }

    public static TestCaseDto mapToTestCaseDto(TestCase testCase) {
        return new TestCaseDto(
                testCase.getId(),
                testCase.getInput(),
                testCase.getExpectedOutput(),
                testCase.getProblems() != null ? testCase.getProblems().getId() : null
        );
    }
}

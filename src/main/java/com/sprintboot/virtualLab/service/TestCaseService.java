package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.TestCaseDto;
import com.sprintboot.virtualLab.entity.Problems;
import com.sprintboot.virtualLab.entity.TestCase;
import com.sprintboot.virtualLab.exception.ResourceNotFoundException;
import com.sprintboot.virtualLab.mapper.TestCaseMapper;
import com.sprintboot.virtualLab.repository.TestCaseRepository;
import com.sprintboot.virtualLab.repository.ProblemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestCaseService implements TestCaseInterface {

    @Autowired
    private TestCaseRepository testCaseRepository;
    @Autowired
    private ProblemsRepository problemsRepository;

    @Override
    public TestCaseDto createTestCase(TestCaseDto testCaseDto) {

        Problems problems = problemsRepository.findById(testCaseDto.getProblemId()).orElseThrow(
        ()-> new ResourceNotFoundException("problem not found exception for given id: "+ testCaseDto.getProblemId()));
        return TestCaseMapper.mapToTestCaseDto(testCaseRepository.save(TestCaseMapper.mapToTestCaseEntity(testCaseDto,problems)));

    }

    @Override
    public List<TestCase> getAllById(Long id) {
        problemsRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("problem not found for this id"));

        return testCaseRepository.findAllByProblems_Id(id);
    }

    @Override
    public String deleteById(Long id) {
        Optional<TestCase> optionalTestCase = testCaseRepository.findById(id);

        if(optionalTestCase.isPresent()){
            testCaseRepository.deleteById(id);
            return "Deleted Successfully ";
        }
        return "Given Id not present";
    }

}

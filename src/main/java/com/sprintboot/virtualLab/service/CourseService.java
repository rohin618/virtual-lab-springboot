package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.CourseDto;
import com.sprintboot.virtualLab.entity.Course;
import com.sprintboot.virtualLab.entity.ProblemSets;
import com.sprintboot.virtualLab.entity.ProblemTopics;
import com.sprintboot.virtualLab.exception.ResourceNotFoundException;
import com.sprintboot.virtualLab.mapper.CourseMapper;
import com.sprintboot.virtualLab.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService implements CourseServiceInterface{

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ProblemTopicsRepository problemTopicsRepository;
    @Autowired
    ProblemTopicOverviewRepository problemTopicOverviewRepository;
    @Autowired
    ProblemSetsRepository problemSetsRepository;
    @Autowired
    ProblemsRepository problemsRepository;


    @Override
    public CourseDto createCourse(CourseDto courseDto) {

        Course course = new Course(
        courseDto.getId(),courseDto.getCourseName(),courseDto.getCourseDescription(),courseDto.getCourseNumbers()
        ,courseDto.getDifficulties(),null
        );
        Course savedCourse = courseRepository.save(course);
        return CourseMapper.mapToCourseDto(savedCourse);
    }

    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return CourseMapper.mapToCourseDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(CourseMapper::mapToCourseDto)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto updateCourse(Long id, CourseDto courseDto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        Course updatedCourse = courseRepository.save(CourseMapper.mapToCourseEntity(courseDto));
        return CourseMapper.mapToCourseDto(updatedCourse);
    }

    @Override
    @Transactional
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        if (course.getProblemTopics() != null) {
            Long problemTopicsId = course.getProblemTopics().getId();
            System.out.println("Deleting ProblemTopics with ID: " + problemTopicsId);

            // Step 1: Delete Problems linked to ProblemSets
            List<ProblemSets> problemSetsList = problemSetsRepository.findAllByProblemTopicsId(problemTopicsId);
            if (problemSetsList != null && !problemSetsList.isEmpty()) {
                for (ProblemSets problemSet : problemSetsList) {
                    if (problemSet.getId() != null) {
                        problemsRepository.deleteAllByProblemSetsId(problemSet.getId()); // Delete Problems
                    }
                }
            }

            // Step 2: Delete ProblemSets linked to ProblemTopics
            problemSetsRepository.deleteAllByProblemTopicsId(problemTopicsId);

            // Step 3: Delete ProblemTopicOverview entries linked to ProblemTopics
            problemTopicOverviewRepository.deleteAllByProblemTopicsId(problemTopicsId);

            // Step 4: Delete ProblemTopics entry
            problemTopicsRepository.delete(course.getProblemTopics());
        }

        // Step 5: Delete the Course
        courseRepository.delete(course);
    }



}
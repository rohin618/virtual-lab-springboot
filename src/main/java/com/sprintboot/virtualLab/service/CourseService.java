package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.CourseDto;
import com.sprintboot.virtualLab.entity.Course;
import com.sprintboot.virtualLab.entity.ProblemTopics;
import com.sprintboot.virtualLab.exception.ResourceNotFoundException;
import com.sprintboot.virtualLab.mapper.CourseMapper;
import com.sprintboot.virtualLab.repository.CourseRepository;
import com.sprintboot.virtualLab.repository.ProblemTopicsRepository;
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
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));

        if (course.getProblemTopics() != null) {
            problemTopicsRepository.delete(course.getProblemTopics());
        }

        courseRepository.delete(course);
    }
}
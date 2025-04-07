package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.ProblemTopicsDto;
import com.sprintboot.virtualLab.entity.Course;
import com.sprintboot.virtualLab.entity.ProblemTopics;
import com.sprintboot.virtualLab.exception.ResourceNotFoundException;
import com.sprintboot.virtualLab.repository.CourseRepository;
import com.sprintboot.virtualLab.repository.ProblemTopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemTopicsService {

    private final ProblemTopicsRepository problemTopicsRepository;
    private final CourseRepository courseRepository;

//    @Autowired
    public ProblemTopicsService(ProblemTopicsRepository problemTopicsRepository, CourseRepository courseRepository){
        this.problemTopicsRepository = problemTopicsRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public ProblemTopicsDto createProblemTopic(ProblemTopicsDto problemTopicsDto) {

        Optional<Course> courseOpt = courseRepository.findById(problemTopicsDto.getCourseId());


        if (courseOpt.isEmpty()) {
            throw new ResourceNotFoundException("Course not found with id: " + problemTopicsDto.getCourseId());
        }

        Course course = courseOpt.get();

        if (course.getProblemTopics() != null) {
            throw new RuntimeException("This course already has a problem topic assigned.");
        }

        ProblemTopics problemTopics = new ProblemTopics(
                null,
                problemTopicsDto.getCourseTitle(),
                problemTopicsDto.getCourseDescription(),
                course
        );

        course.setProblemTopics(problemTopics);
        problemTopicsRepository.save(problemTopics);
        courseRepository.save(course);

        return new ProblemTopicsDto(
                problemTopics.getId(),
                problemTopics.getCourseTitle(),
                problemTopics.getCourseDescription(),
                course.getId()
        );
    }

    public ProblemTopics getProblemTopicByCourseId(Long courseId) {
        return problemTopicsRepository.findByCourseId(courseId)
                .orElseThrow(() -> new RuntimeException("ProblemTopic not found for course ID: " + courseId));
    }

}

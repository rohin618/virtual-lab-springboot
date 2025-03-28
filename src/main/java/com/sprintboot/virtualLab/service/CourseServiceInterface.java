package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.CourseDto;


import java.util.List;

public interface CourseServiceInterface {
    CourseDto createCourse(CourseDto courseDto);
    List<CourseDto> getAllCourses();
    CourseDto updateCourse(Long id, CourseDto courseDto);
    void deleteCourse(Long id);
}

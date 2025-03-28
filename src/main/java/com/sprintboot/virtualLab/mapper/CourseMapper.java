package com.sprintboot.virtualLab.mapper;

import com.sprintboot.virtualLab.dto.CourseDto;
import com.sprintboot.virtualLab.entity.Course;

public class CourseMapper {

    public static Course mapToCourseEntity (CourseDto courseDto){
        return new Course(courseDto.getId(),courseDto.getCourseName(),courseDto.getCourseDescription(),
        courseDto.getCourseNumbers(),courseDto.getDifficulties(),courseDto.getProblemTopics());
    }
    public static CourseDto mapToCourseDto (Course course) {
        return new CourseDto(
                course.getId(),
                course.getCourseName(),
                course.getCourseDescription(),
                course.getCourseNumbers(),
                course.getDifficulties(),
                course.getProblemTopics()
        );
    }
}

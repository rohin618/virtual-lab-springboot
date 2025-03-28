//package com.sprintboot.virtualLab.mapper;
//
//import com.sprintboot.virtualLab.dto.CourseDto;
//import com.sprintboot.virtualLab.dto.ProblemTopicsDto;
//import com.sprintboot.virtualLab.entity.Course;
//import com.sprintboot.virtualLab.entity.ProblemTopics;
//
//public class ProblemTopicsMapper {
//    public static ProblemTopics mapToProblemTopicEntity (ProblemTopicsDto problemTopicsDto){
//        return new ProblemTopics(problemTopicsDto.getId(),problemTopicsDto.getCourseTitle(),problemTopicsDto.getCourseDescription(),problemTopicsDto.getCourseId());
//    }
//    public static ProblemTopicsDto mapToProblemTopicDto (ProblemTopics problemTopics) {
//        return new ProblemTopicsDto(
//                problemTopics.getId(),
//                problemTopics.getCourseTitle(),
//                problemTopics.getCourseDescription(),
//                problemTopics.getCourseId()
//        );
//    }
//}

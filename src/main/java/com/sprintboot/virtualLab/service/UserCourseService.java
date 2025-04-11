package com.sprintboot.virtualLab.service;


import com.sprintboot.virtualLab.configuration.JwtUtil;
import com.sprintboot.virtualLab.entity.CourseProblemEntity;
import com.sprintboot.virtualLab.entity.UserCourseEntity;
import com.sprintboot.virtualLab.exception.ResourceNotFoundException;
import com.sprintboot.virtualLab.repository.CourseProblemRepository;
import com.sprintboot.virtualLab.repository.UserCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCourseService {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserCourseRepository userCourseRepository;

    @Autowired
    private CourseProblemRepository courseProblemRepository;

    public String createNewEnroll(Long courseId,String auth){

        Long userId = jwtUtil.extractUserId(auth);
        if(userId == 0){
            return "invalid";
        }
        UserCourseEntity userCourseEntity = userCourseRepository.save(new UserCourseEntity(userId,courseId));
        return "enrolled";
    }

    public List<UserCourseEntity> getAllEnroll(String auth){
        Long userId = jwtUtil.extractUserId(auth);
        if (userId == null) {
            throw new ResourceNotFoundException("User ID not found in token");
        }
        return userCourseRepository.findAllByUserId(userId);
    }

}

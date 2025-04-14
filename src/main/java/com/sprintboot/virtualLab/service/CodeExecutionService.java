package com.sprintboot.virtualLab.service;


import com.sprintboot.virtualLab.configuration.JwtUtil;
import com.sprintboot.virtualLab.entity.CourseProblemEntity;
import com.sprintboot.virtualLab.exception.ResourceNotFoundException;
import com.sprintboot.virtualLab.repository.CourseProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CodeExecutionService {

   @Autowired
   private CourseProblemRepository courseProblemRepository;
   @Autowired
   private JwtUtil jwtUtil;

    public boolean addOrUpdateUserInfo(String token, Map<String, Object> map, Long problemId) {
        try {
            Long userId = jwtUtil.extractUserId(token);
            if (userId == 0) {
                return false;
            }

            String userCourseStr = String.format("[%d,%d]", userId, problemId);
            String script = (String) map.get("script");

            Optional<CourseProblemEntity> existing = courseProblemRepository.findByUserCourseStr(userCourseStr);

            if (existing.isPresent()) {
                // 🔁 Update script if already present
                CourseProblemEntity entity = existing.get();
                entity.setCurrentCode(script);
                courseProblemRepository.save(entity);
            } else {
                // ➕ Save new entry if not present
                CourseProblemEntity newEntity = new CourseProblemEntity(
                        userCourseStr, problemId, script, ""
                );
                courseProblemRepository.save(newEntity);
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public Optional<CourseProblemEntity> getSolvedCourse(Long problemId, String token) {
        Long userId = jwtUtil.extractUserId(token);
        if (userId == 0) {
            throw new ResourceNotFoundException("User ID not found in token");
        }

        String userProblemStr = String.format("[%d,%d]", userId, problemId);
        return courseProblemRepository.findByUserCourseStr(userProblemStr);
    }



}

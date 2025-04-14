package com.sprintboot.virtualLab.service;


import com.sprintboot.virtualLab.configuration.JwtUtil;
import com.sprintboot.virtualLab.entity.ProblemStatusEntity;
import com.sprintboot.virtualLab.exception.ResourceNotFoundException;
import com.sprintboot.virtualLab.repository.ProblemStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProblemStatusService {

    @Autowired
    private ProblemStatusRepository problemStatusRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public ProblemStatusEntity saveOrUpdateStatus(Long problemOverviewId, String token, String status) {
        Long userId = jwtUtil.extractUserId(token);
        if (userId == 0) {
            throw new ResourceNotFoundException("User ID not found in token");
        }

        String userProblemStr = String.format("[%d,%d]", userId, problemOverviewId);
        Optional<ProblemStatusEntity> problemStatusEntityOptional = problemStatusRepository.findByProblemOverIdUserId(userProblemStr);

        if (problemStatusEntityOptional.isPresent()) {
            ProblemStatusEntity existingStatus = problemStatusEntityOptional.get();

            existingStatus.setStatus(status);
            return problemStatusRepository.save(existingStatus);
        } else {
            // ✅ New status record
            ProblemStatusEntity newStatus = new ProblemStatusEntity(status, userProblemStr);
            return problemStatusRepository.save(newStatus);
        }
    }


    public ProblemStatusEntity getSingleStatus(Long problemOverviewId,String token){
        Long userId = jwtUtil.extractUserId(token);
        if (userId == 0) {
            throw new ResourceNotFoundException("User ID not found in token");
        }
        String userProblemStr = String.format("[%d,%d]", userId, problemOverviewId);
        Optional<ProblemStatusEntity> problemStatusEntityOptional = problemStatusRepository.findByProblemOverIdUserId(userProblemStr);

        if(!problemStatusEntityOptional.isPresent()){
            return null;
        }
        return problemStatusEntityOptional.get();
    }

}

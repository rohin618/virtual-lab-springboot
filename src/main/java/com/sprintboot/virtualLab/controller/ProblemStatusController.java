package com.sprintboot.virtualLab.controller;


import com.sprintboot.virtualLab.entity.ProblemStatusEntity;
import com.sprintboot.virtualLab.service.ProblemStatusService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/problemStatus")
public class ProblemStatusController {

    @Autowired
    private ProblemStatusService problemStatusService;


    @GetMapping("/{id}")
    public ResponseEntity<?> getSingleStatus(@PathVariable Long id, HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }


        ProblemStatusEntity problemStatusEntity = problemStatusService.getSingleStatus(id,token);

        if(problemStatusEntity == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not selected yet");
        }
        else{
            return ResponseEntity.ok(problemStatusEntity);
        }
    }


}

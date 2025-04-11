package com.sprintboot.virtualLab.controller;


import com.sprintboot.virtualLab.configuration.JwtUtil;
import com.sprintboot.virtualLab.entity.UserCourseEntity;
import com.sprintboot.virtualLab.service.UserCourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userEnroll")
public class UserCourseEnrollController {


    @Autowired
    private UserCourseService userCourseService;

    @PostMapping("/{courseId}")
    public ResponseEntity<?> newEnrollUser(@PathVariable Long courseId, HttpServletRequest httpServletRequest){

        String authHeader = httpServletRequest.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            String res = userCourseService.createNewEnroll(courseId,token);
            if(res.equals("invalid"))return ResponseEntity.badRequest().body("Invalid User");
        }
        return ResponseEntity.ok("Successfully Enrolled");
    }

    @GetMapping
    public ResponseEntity<?> getAllCourse(HttpServletRequest httpServletRequest){
        String authHeader = httpServletRequest.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            return ResponseEntity.ok(userCourseService.getAllEnroll(token));
        }
        return ResponseEntity.badRequest().body("Invalid User ID");
    }
}

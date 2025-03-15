package com.sprintboot.virtualLab.controller;


import com.sprintboot.virtualLab.configuration.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<String> getUserName(@RequestHeader("Authorization") String token){
        if(token != null && token.startsWith("Bearer")){
            String jwtToken = token.substring(7);
            try {
                if(jwtUtil.isTokenValid(jwtToken)){
                    String userName = jwtUtil.extractUsername(jwtToken);

                    return ResponseEntity.ok("Welcome MR/MS: " + userName);
                }
            }catch (Exception e){
                return ResponseEntity.badRequest().body("In correct entry");
            }
        }
        return ResponseEntity.badRequest().body("Incorrect entry");
    }
}

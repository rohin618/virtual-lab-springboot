package com.sprintboot.virtualLab.controller;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/code")
public class CodeExecutionController {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String JDoodle_URL = "https://api.jdoodle.com/v1/execute";
    private final String CLIENT_ID = "79ba9d56a10c91473bfc0f6628ed66a";
    private final String CLIENT_SECRET = "996908c38375318303b65f936a04499da170f71469ca348dea39254d08fd833c";
    @PostMapping("/run")
    public ResponseEntity<?> runCode(@RequestBody Map<String, Object> payload) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("clientId", CLIENT_ID);
        requestBody.put("clientSecret", CLIENT_SECRET);
        requestBody.put("script", payload.get("script"));
        requestBody.put("stdin", payload.get("stdin"));
        requestBody.put("language", payload.get("language"));
        requestBody.put("versionIndex", payload.get("versionIndex"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(JDoodle_URL, request, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}

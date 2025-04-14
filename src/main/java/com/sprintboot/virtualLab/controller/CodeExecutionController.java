package com.sprintboot.virtualLab.controller;

import com.sprintboot.virtualLab.entity.CourseProblemEntity;
import com.sprintboot.virtualLab.service.CodeExecutionService;
import com.sprintboot.virtualLab.service.ProblemStatusService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/api/code")
public class CodeExecutionController {

    @Autowired
    private CodeExecutionService codeExecutionService;
    @Autowired
    private ProblemStatusService problemStatusService;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String JDoodle_URL = "https://api.jdoodle.com/v1/execute";
    private final String CLIENT_ID = "79ba9d56a10c91473bfc0f6628ed66a";
    private final String CLIENT_SECRET = "996908c38375318303b65f936a04499da170f71469ca348dea39254d08fd833c";

    @PostMapping("/run/{id}/{problemOverviewId}")
    public ResponseEntity<?> runCode(@RequestBody Map<String, Object> payload,
                                     HttpServletRequest request,
                                     @PathVariable Long id, @PathVariable Long problemOverviewId) {
        String script = (String) payload.get("script");
        String language = (String) payload.get("language");
        String versionIndex = (String) payload.get("versionIndex");

        List<Map<String, String>> testCases = (List<Map<String, String>>) payload.get("testCases");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String authHeader = request.getHeader("Authorization");
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        List<Map<String, Object>> results = new ArrayList<>();

        int testCasePass = 0;

        for (Map<String, String> testCase : testCases) {
            String input = testCase.get("input");
            String expected = testCase.get("expected");

            Map<String, Object> jdoodlePayload = new HashMap<>();
            jdoodlePayload.put("clientId", CLIENT_ID);
            jdoodlePayload.put("clientSecret", CLIENT_SECRET);
            jdoodlePayload.put("script", script);
            jdoodlePayload.put("stdin", input);
            jdoodlePayload.put("language", language);
            jdoodlePayload.put("versionIndex", versionIndex);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(jdoodlePayload, headers);

            try {
                ResponseEntity<Map> response = restTemplate.postForEntity(JDoodle_URL, entity, Map.class);
                String output = response.getBody().get("output").toString().trim();

                boolean passed = output.equals(expected.trim());
                if(passed)testCasePass++;

                Map<String, Object> result = new HashMap<>();
                result.put("input", input);
                result.put("expected", expected);
                result.put("output", output);
                result.put("passed", passed);

                results.add(result);
            } catch (Exception e) {
                Map<String, Object> errorResult = new HashMap<>();
                errorResult.put("input", input);
                errorResult.put("expected", expected);
                errorResult.put("output", "Error: " + e.getMessage());
                errorResult.put("passed", false);

                results.add(errorResult);
            }
        }

        // Save user execution info
        if (token != null) {
            Map<String, Object> executionInfo = new HashMap<>();
            executionInfo.put("script", script);
            codeExecutionService.addOrUpdateUserInfo(token, executionInfo, id);

            if(testCasePass == testCases.size()){
                problemStatusService.saveOrUpdateStatus(problemOverviewId,token,"complete");
            }else{
                problemStatusService.saveOrUpdateStatus(problemOverviewId,token,"Attempt");
            }

        }

        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSolvedProblem(@PathVariable Long id, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        Optional<CourseProblemEntity> courseProblemEntityOptional = codeExecutionService.getSolvedCourse(id, token);

        if (courseProblemEntityOptional.isPresent()) {
            return ResponseEntity.ok(courseProblemEntityOptional.get()); // returns 200 OK with data
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course problem not found"); // returns 404 with message
        }
    }


}

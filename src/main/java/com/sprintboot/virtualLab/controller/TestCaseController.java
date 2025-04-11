package com.sprintboot.virtualLab.controller;


import com.sprintboot.virtualLab.dto.TestCaseDto;
import com.sprintboot.virtualLab.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testcase")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

    @PostMapping
    public ResponseEntity<?> createTestCase(@RequestBody TestCaseDto testCaseDto){
        return ResponseEntity.ok(testCaseService.createTestCase(testCaseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllById(@PathVariable Long id){
        return ResponseEntity.ok(testCaseService.getAllById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTestCase(@PathVariable Long id){
        String res = testCaseService.deleteById(id);
        if(res.equals("Given Id not present"))return ResponseEntity.badRequest().body("Given Id not found ");
        return ResponseEntity.ok(res);
    }

}

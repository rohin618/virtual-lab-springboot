package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserServiceInterface{
    ResponseEntity<String> signIn(UserDto userDto);
//    ResponseEntity<String> logIn(String userName,String password);
}

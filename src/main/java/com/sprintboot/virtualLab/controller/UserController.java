package com.sprintboot.virtualLab.controller;

import com.sprintboot.virtualLab.configuration.JwtUtil;
import com.sprintboot.virtualLab.dto.UserDto;
import com.sprintboot.virtualLab.entity.UserEntity;
import com.sprintboot.virtualLab.repository.UserRepository;
import com.sprintboot.virtualLab.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/auth") // ✅ Updated to match standard API request mapping
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    // ✅ Sign-Up Endpoint (User Registration)
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody UserDto userDto) {
        return userService.signIn(userDto);
    }

    // ✅ Login Endpoint (Return JWT + Role-based response)
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody UserDto userDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUserName(), userDto.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed");
        }

        // ✅ Find the user from the database
        Optional<UserEntity> userOptional = userRepository.findByUserName(userDto.getUserName());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        UserEntity user = userOptional.get();

        // ✅ Generate JWT Token
        String token = jwtUtil.generateToken(user.getUserName());

        // ✅ Return response based on user role
        return ResponseEntity.ok(Map.of(
                "role", user.getRole(),
                "token", token
        ));
    }
}

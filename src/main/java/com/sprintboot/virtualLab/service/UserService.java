package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.UserDto;
import com.sprintboot.virtualLab.entity.UserEntity;
import com.sprintboot.virtualLab.exception.ResourceNotFoundException;
import com.sprintboot.virtualLab.mapper.UserMapper;
import com.sprintboot.virtualLab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<String> signIn(UserDto userDto) {

        if(userRepository.existsByUserName(userDto.getUserName())){
           return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("UserName Already Exists");
        }
        // for encode the pass word
        String encodePass = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePass);


        UserEntity userEntity = userRepository.save(UserMapper.mapToUserEntity(userDto));
        System.out.println(userEntity.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("user Created Successfully");
    }

//    @Override
//    public ResponseEntity<String> logIn(String userName,String userPass) {
//        Optional<UserEntity> optionalUser = userRepository.findByUserName(userName);
//
//        if (optionalUser.isPresent()) {
//            UserEntity user = optionalUser.get();
//            if (user.getPassword().equals(userPass)) {
//                return ResponseEntity.ok("User Exists");
//            } else {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password does not match");
//            }
//        }
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user  = userRepository.findByUserName(username).orElseThrow(()->new UsernameNotFoundException("ser not Found " + username));
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), Collections.emptyList());
    }
}

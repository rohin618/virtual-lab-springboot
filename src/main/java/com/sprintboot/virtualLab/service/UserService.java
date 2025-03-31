package com.sprintboot.virtualLab.service;

import com.sprintboot.virtualLab.dto.UserDto;
import com.sprintboot.virtualLab.entity.UserEntity;
import com.sprintboot.virtualLab.exception.ResourceNotFoundException;
import com.sprintboot.virtualLab.mapper.UserMapper;
import com.sprintboot.virtualLab.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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
        if (userRepository.existsByUserName(userDto.getUserName())) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("UserName Already Exists");
        }

        // Encode password
        String encodePass = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePass);

        // Assign "USER" role by default if no role is set
        if (userDto.getRole() == null || userDto.getRole().isEmpty()) {
            userDto.setRole("USER");
        }

        UserEntity userEntity = userRepository.save(UserMapper.mapToUserEntity(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body("User Created Successfully");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not Found " + username));

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                authorities
        );
    }
}

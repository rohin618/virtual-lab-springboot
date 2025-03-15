package com.sprintboot.virtualLab.repository;

import com.sprintboot.virtualLab.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Check if a user exists by username
    boolean existsByUserName(String userName);

    // Find user by username
    Optional<UserEntity> findByUserName(String userName);
}

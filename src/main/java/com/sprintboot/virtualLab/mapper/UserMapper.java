package com.sprintboot.virtualLab.mapper;

import com.sprintboot.virtualLab.dto.UserDto;
import com.sprintboot.virtualLab.entity.UserEntity;

public class UserMapper {
    public static UserEntity mapToUserEntity(UserDto userDto) {
        return new UserEntity(userDto.getId(), userDto.getUserName(), userDto.getPassword(), userDto.getRole());
    }

    public static UserDto mapToUserDto(UserEntity userEntity) {
        return new UserDto(userEntity.getId(), userEntity.getUserName(), userEntity.getPassword(), userEntity.getRole());
    }
}

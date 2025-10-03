package org.example.task6.mappers;

import java.util.List;

import org.example.task6.config.MapstructConfig;
import org.example.task6.dto.UserDto;
import org.example.task6.entities.User;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public interface UserMapper {
    UserDto toUserDto(User user);
    List<UserDto> toListUsersDto(List<User> users);
}

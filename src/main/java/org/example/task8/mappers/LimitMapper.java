package org.example.task8.mappers;

import org.example.task6.config.MapstructConfig;
import org.example.task8.dto.UserLimitDto;
import org.example.task8.entityes.UserLimitEntity;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public interface LimitMapper {

    UserLimitDto toUserLimitDto(UserLimitEntity entity);
}

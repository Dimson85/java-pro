package org.example.task8.mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.example.task8.dto.UserLimitDto;
import org.example.task8.entityes.UserLimitEntity;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-13T15:17:55+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class LimitMapperImpl implements LimitMapper {

    @Override
    public UserLimitDto toUserLimitDto(UserLimitEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Integer userId = null;
        BigDecimal dailyLimit = null;
        BigDecimal defaultLimit = null;
        LocalDateTime lastReset = null;

        userId = entity.getUserId();
        dailyLimit = entity.getDailyLimit();
        defaultLimit = entity.getDefaultLimit();
        lastReset = entity.getLastReset();

        UserLimitDto userLimitDto = new UserLimitDto( userId, dailyLimit, defaultLimit, lastReset );

        return userLimitDto;
    }
}

package org.example.task8.servises;

import static org.example.task8.utils.Constant.DEFAULT_DAILY_LIMIT;
import static org.example.task8.utils.Constant.ZERO_LIMIT;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task8.dto.LimitResponse;
import org.example.task8.dto.UserLimitDto;
import org.example.task8.entityes.UserLimitEntity;
import org.example.task8.mappers.LimitMapper;
import org.example.task8.repositories.UserLimitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LimitService {

    private final UserLimitRepository limitRepository;
    private final LimitMapper limitMapper;

    public UserLimitDto getLimitById(Integer userId) {
        var entity = limitRepository.findById(userId);

        if (entity.isPresent()) {
            return limitMapper.toUserLimitDto(entity.get());
        }

        var savedEntity = limitRepository.save(new UserLimitEntity(userId, DEFAULT_DAILY_LIMIT, ZERO_LIMIT, DEFAULT_DAILY_LIMIT, LocalDateTime.now()));
        return limitMapper.toUserLimitDto(savedEntity);
    }

    @Transactional
    public LimitResponse reserve(Integer userId, BigDecimal amount) {
        var optional = limitRepository.findById(userId);
        if (optional.isPresent()) {
            return checkLimit(optional.get(), amount);
        }
        var savedEntity = limitRepository.save(new UserLimitEntity(userId, DEFAULT_DAILY_LIMIT, ZERO_LIMIT, DEFAULT_DAILY_LIMIT, LocalDateTime.now()));
        return checkLimit(savedEntity, amount);
    }

    private LimitResponse checkLimit(UserLimitEntity entity, BigDecimal amount) {
        if (entity.getReserve().compareTo(BigDecimal.ZERO) == 0) {
            var subtract = entity.getDailyLimit().subtract(amount);
            if (subtract.compareTo(BigDecimal.ZERO) < 0) {
                return new LimitResponse(false, "Операция не может быть выполнена, лимит меньше запрашиваемой суммы");
            }
            entity.setDailyLimit(subtract);
            entity.setReserve(amount);
            limitRepository.save(entity);
            return new LimitResponse(true, "Лимит списан");
        } else {
            return new LimitResponse(false, "Операция не может быть выполнена, лимит в резерве");
        }
    }

    public LimitResponse confirmPayment(Integer userId, boolean isSuccess) {
        var optional = limitRepository.findById(userId);
        return optional.map(entity -> confirmOrReset(entity, isSuccess))
            .orElseThrow(() -> new IllegalArgumentException("У пользователя с id = %s нет ".formatted(userId)));
    }

    private LimitResponse confirmOrReset(UserLimitEntity entity, boolean isSuccess) {
        if (isSuccess) {
            entity.setReserve(BigDecimal.ZERO);
        } else {
            entity.setDailyLimit(entity.getDailyLimit().add(entity.getReserve()));
            entity.setReserve(BigDecimal.ZERO);
        }
        limitRepository.save(entity);
        return new LimitResponse(true, "Запрос обработан");
    }
}

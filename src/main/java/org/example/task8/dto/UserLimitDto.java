package org.example.task8.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserLimitDto(
    @Schema(description = "Id пользователя")
    Integer userId,
    @Schema(description = "Остаток по дневному лимиту")
    BigDecimal dailyLimit,
    @Schema(description = "Установленный суточный лимит")
    BigDecimal defaultLimit,
    @Schema(description = "Дата последнего восстановления лимита")
    LocalDateTime lastReset
) {
}

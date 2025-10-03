package org.example.task6.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.task6.dto.ApiErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {
    /**
     * Общий обработчик ошибок.
     *
     * @param t необработанная ошибка
     * @return статус 500, обёртка с сообщением об ошибке
     */
    @ExceptionHandler(Throwable.class)
    public ApiErrorResponse handleThrowable(Throwable t) {
        log.error(t.getMessage(), t);
        return new ApiErrorResponse(t.getLocalizedMessage());
    }
}

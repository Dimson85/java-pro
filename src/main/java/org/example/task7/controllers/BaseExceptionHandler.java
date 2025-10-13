package org.example.task7.controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.task6.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = ProductController.class)
@Slf4j
public class BaseExceptionHandler {
    /**
     * Общий обработчик ошибок.
     *
     * @param t необработанная ошибка
     * @return статус 500, обёртка с сообщением об ошибке
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ApiErrorResponse handleThrowable(Throwable t) {
        return new ApiErrorResponse(t.getLocalizedMessage());
    }
}

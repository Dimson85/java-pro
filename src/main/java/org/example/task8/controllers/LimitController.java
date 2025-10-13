package org.example.task8.controllers;

import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task8.dto.LimitResponse;
import org.example.task8.dto.UserLimitDto;
import org.example.task8.servises.LimitService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/limits")
@Validated
@Slf4j
public class LimitController {

    private final LimitService limitService;

    @GetMapping("/{userId:\\d+}")
    public UserLimitDto getLimitById(@PathVariable @Positive Integer userId) {
        return limitService.getLimitById(userId);
    }

    @PostMapping("/{userId:\\d+}/reserve")
    public LimitResponse reserve(@PathVariable("userId") Integer userId, @RequestParam BigDecimal amount) {
        return limitService.reserve(userId, amount);
    }

    @PostMapping("/{userId:\\d+}/confirm")
    public LimitResponse confirmPayment(@PathVariable("userId") Integer userId, @RequestParam boolean isSuccess) {
        return limitService.confirmPayment(userId, isSuccess);
    }
}

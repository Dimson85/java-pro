package org.example.task8.schedulers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task8.repositories.UserLimitRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Шедулер обновления суточных лимитов.
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "edu.limit-scheduler.enable", havingValue = "true", matchIfMissing = true)
public class LimitScheduler {

    private final UserLimitRepository limitRepository;

    @Scheduled(cron = "0 46 15 * * ?")
    @Transactional
    public void scheduleLimit() {
        log.info("Scheduling limit запущен");
        try {
            limitRepository.setDefaultLimit();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}

package org.example.java.kafka.scheduler;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.java.kafka.KafkaService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Шедулер закрытия опции AutoRepo.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FruitScheduler {

    private final KafkaService kafkaService;

    /**
     * Периодическое задание по отправке фруктов.
     */
    @Scheduled(
        initialDelay = 10000L,
        fixedRate = Long.MAX_VALUE,
        scheduler = "fruitTaskExecutor")
    public void scheduleFruits() {
        log.info("Scheduling fruit");
        List.of("apple", "banana", "orange", "grape", "strawberry", "mango", "pineapple", "peach", "pear", "cherry")
            .forEach(fruit -> {
                kafkaService.sendFruit(fruit);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
    }
}

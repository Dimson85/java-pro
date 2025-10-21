package org.example.java.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.Lifecycle;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Component;

/**
 * Класс для запуска контейнеров кафки.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaListenerContainersStarter {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    /**
     * Запускает контейнеры кафки.
     */
    public void start() {
        log.info("Запускаются слушатели Kafka-сообщений");
        kafkaListenerEndpointRegistry
            .getListenerContainers()
            .forEach(Lifecycle::start);
    }
}

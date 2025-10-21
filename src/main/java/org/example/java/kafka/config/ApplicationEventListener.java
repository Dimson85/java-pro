package org.example.java.kafka.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class ApplicationEventListener {

    private final KafkaListenerContainersStarter containersStarter;

    @EventListener(ApplicationReadyEvent.class)
    public void handleApplicationReadyEvent() {
        containersStarter.start();
    }
}

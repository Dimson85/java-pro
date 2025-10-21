package org.example.java.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FruitKafkaReceiver {

    @KafkaListener(
        containerFactory = "fruitResponseListenerContainerFactory",
        groupId = "${javapro.kafka.consumer.group-id}",
        topics = "${javapro.kafka.consumer.topic.fruit}",
        autoStartup = "false")
    public void receive(String fruit) {
        log.info("Получено сообщение с фруктом: {}", fruit);
    }
}

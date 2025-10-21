package org.example.java.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, String> fruitKafkaTemplate;

    @Value("${javapro.kafka.producer.topic.fruit}")
    private String fruitTopic;

    public void sendFruit(String fruit) {
        fruitKafkaTemplate.send(fruitTopic, fruit);
        log.info("Sending fruit {}", fruit);
    }
}

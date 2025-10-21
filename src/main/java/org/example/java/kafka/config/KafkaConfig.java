package org.example.java.kafka.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "javapro")
@ConditionalOnProperty(value = "kafka.enabled", havingValue = "true", matchIfMissing = true)
public class KafkaConfig {

    @NestedConfigurationProperty
    private KafkaProperties kafka = new KafkaProperties();

    @Bean
    public ProducerFactory<String, String> fruitKafkaProducerFactory(ObjectProvider<SslBundles> sslBundles) {
        var producerProperties = this.kafka.buildProducerProperties(sslBundles.getIfAvailable());
        producerProperties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        producerProperties.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);
        producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(producerProperties);
    }

    @Bean
    public KafkaTemplate<String, String> fruitKafkaTemplate(
        ProducerFactory<String, String> fruitKafkaProducerFactory) {
        var template = new KafkaTemplate<>(fruitKafkaProducerFactory);
        template.setObservationEnabled(true);
        template.setAllowNonTransactional(true);
        return template;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> fruitResponseListenerContainerFactory(
        ObjectProvider<SslBundles> sslBundles) {
        var consumerProperties = kafka.buildConsumerProperties(sslBundles.getIfAvailable());
        var consumerFactory = new DefaultKafkaConsumerFactory<String, String>(consumerProperties);
        var containerFactory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        containerFactory.setConsumerFactory(consumerFactory);
        containerFactory.setConcurrency(kafka.getListener().getConcurrency());
        containerFactory.getContainerProperties().setSyncCommits(true);
        return containerFactory;
    }
}

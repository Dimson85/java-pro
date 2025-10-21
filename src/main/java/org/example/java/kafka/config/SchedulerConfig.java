package org.example.java.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Конфигурации шедулеров.
 */
@Configuration
public class SchedulerConfig {

    @Bean
    public TaskScheduler fruitTaskExecutor() {
        var executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(1);
        executor.setThreadNamePrefix("fruit-task-");
        executor.initialize();
        return executor;
    }
}

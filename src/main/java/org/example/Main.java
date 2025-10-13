package org.example;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

package org.example.task4;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("org.example")
@Import(DBConfig.class)
public class ApiConfig {
}

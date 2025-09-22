package org.example.task6.config;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

/**
 * Общая конфигурация мапстракта.
 */
@MapperConfig(componentModel = SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public class MapstructConfig {
}

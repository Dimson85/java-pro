package org.example.task6.dto;

import java.util.List;

import org.example.task6.entities.Product;


public record UserDto(Long id, String userName, List<Product> products) {
}

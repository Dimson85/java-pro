package org.example.task6.dto;

import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.task6.entities.ProductType;
import org.example.task6.entities.User;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductDto(Long id,
                         @NotEmpty(message = "accountNumber не должен быть пустым") String accountNumber,
                         BigDecimal balance,
                         ProductType productType,
                         @JsonIgnore User user,
                         Long userId) {
}


package org.example.task6.services;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.task6.dto.ProductDto;
import org.example.task6.entities.Product;
import org.example.task6.entities.User;
import org.example.task6.mappers.ProductMapper;
import org.example.task6.repositories.ProductRepository;
import org.example.task6.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ProductMapper mapper;

    public List<ProductDto> findProductsByUserId(Long userId) {
        return mapper.toListProductsDto(productRepository.findByUserId(userId));
    }

    public ProductDto findProductById(Long id) {
        return mapper.toProductDto(productRepository.findById(id).orElse(new Product()));
    }

    public void saveProduct(ProductDto dto) {
        userRepository.findById(dto.userId())
            .map(entity -> prepare(dto, entity))
            .map(userService::saveUser)
            .orElseThrow(() -> new RuntimeException("Пользователя с таким id не существует"));
    }

    private User prepare(ProductDto dto, User entity) {
        entity.getProducts().add(new Product(null, dto.accountNumber(), dto.balance(), dto.productType(), entity));
        return entity;
    }
}

package org.example.task6.controllers;

import jakarta.validation.Valid;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.task6.dto.ProductDto;
import org.example.task6.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
@Validated
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @GetMapping()
    public List<ProductDto> findByUserId(@RequestParam("userId") Long userId) {
        return productService.findProductsByUserId(userId);
    }

    @PostMapping()
    public ResponseEntity saveProduct(@Valid @RequestBody ProductDto dto) {
        log.info("Saving product {}", dto);
        productService.saveProduct(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

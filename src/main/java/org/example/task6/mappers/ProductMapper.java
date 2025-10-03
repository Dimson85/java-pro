package org.example.task6.mappers;

import java.util.List;

import org.example.task6.config.MapstructConfig;
import org.example.task6.dto.ProductDto;
import org.example.task6.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface ProductMapper {
    @Mapping(target = "userId", expression = "java(product.getUser().getId())")
    ProductDto toProductDto(Product product);

    List<ProductDto> toListProductsDto(List<Product> products);
}

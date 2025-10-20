package org.example.task6.mappers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.task6.dto.ProductDto;
import org.example.task6.entities.Product;
import org.example.task6.entities.ProductType;
import org.example.task6.entities.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-13T15:17:55+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        Long id = null;
        String accountNumber = null;
        BigDecimal balance = null;
        ProductType productType = null;
        User user = null;

        id = product.getId();
        accountNumber = product.getAccountNumber();
        balance = product.getBalance();
        productType = product.getProductType();
        user = product.getUser();

        Long userId = product.getUser().getId();

        ProductDto productDto = new ProductDto( id, accountNumber, balance, productType, user, userId );

        return productDto;
    }

    @Override
    public List<ProductDto> toListProductsDto(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( products.size() );
        for ( Product product : products ) {
            list.add( toProductDto( product ) );
        }

        return list;
    }
}

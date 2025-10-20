package org.example.task6.mappers;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.example.task6.dto.UserDto;
import org.example.task6.entities.Product;
import org.example.task6.entities.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-13T15:17:55+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String userName = null;
        List<Product> products = null;

        id = user.getId();
        userName = user.getUserName();
        List<Product> list = user.getProducts();
        if ( list != null ) {
            products = new ArrayList<Product>( list );
        }

        UserDto userDto = new UserDto( id, userName, products );

        return userDto;
    }

    @Override
    public List<UserDto> toListUsersDto(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( toUserDto( user ) );
        }

        return list;
    }
}

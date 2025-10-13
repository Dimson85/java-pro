package org.example.task6.services;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.task6.dto.UserDto;
import org.example.task6.entities.User;
import org.example.task6.mappers.UserMapper;
import org.example.task6.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class UserService implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserMapper mapper;

    public List<UserDto> findAllUsers() {
        return mapper.toListUsersDto(userRepository.findAll());
    }

    public UserDto findUser(Long id) {
        return mapper.toUserDto(userRepository.findById(id).orElse(new User()));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void run(String... args) {
//        System.out.println("\n\n1. Наполнение таблицы: ");
//        findAllUsers().forEach(System.out::println);
    }
}

package org.example;

import java.util.List;
import java.util.Optional;

import lombok.SneakyThrows;
import org.example.task4.ApiConfig;
import org.example.task4.User;
import org.example.task4.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApiConfig.class);

        try (context) {
            UserService userService = context.getBean(UserService.class);
            System.out.println("=== User Management Demo ===");

            //Удалим все
            userService.deleteAll();

            // Создание пользователей
            System.out.println("1. Creating users:");
            User user1 = userService.create(new User(null, "john"));
            User user2 = userService.create(new User(null, "john2"));
            User user3 = userService.create(new User(null, "john3"));

            System.out.println("Created: " + user1);
            System.out.println("Created: " + user2);
            System.out.println("Created: " + user3);

            // Получение всех пользователей
            System.out.println("2. All users: ");
            List<User> allUsers = userService.getAllUsers();
            allUsers.forEach(System.out::println);

            // Получение пользователя по ID
            System.out.println("3. by ID:");
            Optional<User> foundUser = userService.getUserById(user2.getId());
            foundUser.ifPresentOrElse(
                user -> System.out.println("Найден: " + user),
                () -> System.out.println("Не найден")
            );

            // Обновление пользователя
            System.out.println("5. Update user:");
            Optional<User> updatedUser = userService.updateUser(user1.getId(), "john_updated");
            updatedUser.ifPresentOrElse(
                user -> System.out.println("Updated: " + user),
                () -> System.out.println("Update failed")
            );

            // Удаление пользователя
            System.out.println("6. Delete user:");
            boolean deleted = userService.deleteUser(user3.getId());
            System.out.println("User deleted: " + deleted);

            // Проверка после удаления
            System.out.println("7. Users after deletion:");
            userService.getAllUsers().forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

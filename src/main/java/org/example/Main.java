package org.example;

import java.util.Arrays;
import java.util.List;

import lombok.SneakyThrows;
import org.example.task2.Employee;
import org.example.task2.StreamConvertor;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        var nullList = Arrays.asList(null, "", "ABC", "ABC", "QQ");
        var streamApi = new StreamConvertor();
        var list = Arrays.asList(
            "Мама мыла Окно, окно было довольно",
            "кровать"
        );
        var input = "Мама мыла Окно, окно было довольно";
        var employees = List.of(
            new Employee("Иван", 35, "Инженер"),
            new Employee("Петр", 42, "Инженер"),
            new Employee("Мария", 28, "Менеджер"),
            new Employee("Алексей", 45, "Менеджер"),
            new Employee("Светлана", 39, "Инженер"),
            new Employee("Дмитрий", 50, "Инженер"),
            new Employee("Ольга", 31, "Аналитик")
        );

        System.out.println("--------- convertWithoutEmpty: ");
        System.out.println(streamApi.convertWithoutEmpty(nullList));

        System.out.println("---------- getCountOfDifferentLetters: ");
        System.out.println(streamApi.getCountOfDifferentLetters(nullList));

        System.out.println("---------- getLongestWorld: ");
        System.out.println(streamApi.getLongestWorld(nullList));

        System.out.println("---------- getThirdNumber: ");
        System.out.println(streamApi.getThirdNumber(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13)));

        System.out.println("---------- getOldestEmployee: ");
        System.out.println(streamApi.getOldestEmployee(employees, "Инженер"));

        System.out.println("---------- getAverageAge: ");
        System.out.println(streamApi.getAverageAge(employees, "Инженер"));

        System.out.println("---------- getMapWords: ");
        System.out.println(streamApi.getMapWords(input));

        System.out.println("---------- getLongestWord: ");
        System.out.println(streamApi.getLongestWord(list));
    }
}

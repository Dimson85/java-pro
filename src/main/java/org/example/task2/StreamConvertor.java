package org.example.task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class StreamConvertor {

    public List<String> convertWithoutEmpty(List<String> input) {
        return CollectionUtils.emptyIfNull(input).stream()
            .filter(StringUtils::isNotBlank)
            .distinct()
            .toList();
    }

    public long getCountOfDifferentLetters(List<String> input) {
        return CollectionUtils.emptyIfNull(input).stream()
            .filter(StringUtils::isNotBlank)
            .flatMapToInt(String::chars)
            .mapToObj(Character::toString)
            .distinct()
            .count();
    }

    public String getLongestWorld(List<String> input) {
        return CollectionUtils.emptyIfNull(input).stream()
            .filter(StringUtils::isNotBlank)
            .map(String::trim)
            .sorted()
            .findFirst()
            .orElse("");
    }

    public int getThirdNumber(List<Integer> input) {
        return CollectionUtils.emptyIfNull(input).stream()
            .sorted(Comparator.reverseOrder())
            .skip(2)
            .findFirst()
            .orElse(0);
    }

    public List<String> getOldestEmployee(List<Employee> input, String position) {
        return CollectionUtils.emptyIfNull(input).stream()
            .filter(e -> checkNullString(position).equals(e.position()))
            .sorted(Comparator.comparingInt(Employee::age).reversed())
            .limit(3L)
            .map(Employee::name)
            .toList();
    }

    public double getAverageAge(List<Employee> input, String position) {
        return CollectionUtils.emptyIfNull(input).stream()
            .filter(e -> checkNullString(position).equals(e.position()))
            .mapToInt(Employee::age)
            .average()
            .orElse(0);
    }

    public Map<Integer, List<String>> getMapWords(String input) {
        return Arrays.stream(checkNullString(input).split(" "))
            .map(w -> w.replaceAll("[^\\p{L}]", "").toLowerCase())
            .filter(w -> !w.isBlank())
            .distinct()
            .collect(Collectors.groupingBy(String::length, Collectors.toList()));
    }

    public String getLongestWord(List<String> input) {
        return CollectionUtils.emptyIfNull(input).stream()
            .flatMap(string -> Arrays.stream(checkNullString(string).split(" ")))
            .map(w -> w.replaceAll("[^\\p{L}]", ""))
            .max(Comparator.comparing(String::length))
            .orElse("");
    }

    private String checkNullString(String string) {
        return ObjectUtils.defaultIfNull(string, StringUtils.EMPTY);
    }
}

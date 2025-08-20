package org.example;

import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import org.example.enums.TestResult;
import org.example.processors.TestDetail;
import org.example.processors.TestRunner;
import org.example.testClasses.ExampleTest;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Map<TestResult, List<TestDetail>> results = TestRunner.runTests(ExampleTest.class);

        for (Map.Entry<TestResult, List<TestDetail>> entry : results.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().size());
            for (TestDetail detail : entry.getValue()) {
                System.out.println(" ---------------- " + detail.testName() +
                    (detail.exception() != null ?
                        " (" + detail.exception().getMessage() + ")" : ""));
            }
        }
    }
}






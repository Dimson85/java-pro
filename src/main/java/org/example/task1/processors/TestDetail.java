package org.example.task1.processors;

import org.example.task1.enums.TestResult;

public record TestDetail(TestResult result, String testName, Throwable exception) {
}

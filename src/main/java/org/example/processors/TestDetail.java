package org.example.processors;

import org.example.enums.TestResult;

public record TestDetail(TestResult result, String testName, Throwable exception) {
}

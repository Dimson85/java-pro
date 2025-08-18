package org.example.testClasses;

import org.example.Exceptions.TestAssertionError;
import org.example.annotations.AfterEach;
import org.example.annotations.AfterSuite;
import org.example.annotations.BeforeEach;
import org.example.annotations.BeforeSuite;
import org.example.annotations.Disabled;
import org.example.annotations.Order;
import org.example.annotations.Test;

public class ExampleTest {

    @BeforeSuite
    static void beforeAllTests() {
        System.out.println("Before all tests @BeforeSuite");
    }

    @AfterSuite
    static void afterAllTests() {
        System.out.println("After all tests @AfterSuite");
    }

    @BeforeEach
    void beforeEachTest() {
        System.out.println("Before each test @BeforeEach");
    }

    @AfterEach
    void afterEachTest() {
        System.out.println("After each test @AfterEach");
    }

    @Test(name = "First test", priority = 10)
    @Order(priority = 1)
    void test1() {
        System.out.println("name = First test priority = 10 @Order(priority = 1)");
    }

    @Test
    @Order(priority = 10)
    void test2() {
        System.out.println("Running test2 @Order(priority = 10)");
        throw new TestAssertionError("Assertion failed");
    }

    @Test
    @Order(priority = 8)
    void test3() {
        System.out.println("Running test3 @Order(priority = 8)");
        throw new TestAssertionError("Assertion failed");
    }

    @Test
    @Order(priority = 2)
    void test4() {
        System.out.println("Running test4 @Order(priority = 2)");
        throw new TestAssertionError("Assertion failed");
    }

    @Test
    @Disabled
    void disabledTest() {
        System.out.println("This test is disabled");
    }

    @Test(priority = 3)
    void testWithException() {
        System.out.println("Running test with exception  @Test(priority = 3)");
        throw new RuntimeException("Unexpected error");
    }

    @Test(priority = 3)
    @Order(priority = 4)
    void testWithException2() {
        System.out.println("Running test with exception2  @Test(priority = 3) @Order(priority = 4)");
        throw new RuntimeException("Unexpected error");
    }
}

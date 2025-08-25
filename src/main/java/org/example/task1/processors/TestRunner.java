package org.example.task1.processors;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.task1.Exceptions.BadTestClassError;
import org.example.task1.Exceptions.TestAssertionError;
import org.example.task1.annotations.AfterEach;
import org.example.task1.annotations.AfterSuite;
import org.example.task1.annotations.BeforeEach;
import org.example.task1.annotations.BeforeSuite;
import org.example.task1.annotations.Disabled;
import org.example.task1.annotations.Order;
import org.example.task1.annotations.Test;
import org.example.task1.enums.TestResult;

public class TestRunner {

    public static Map<TestResult, List<TestDetail>> runTests(Class<?> clazz) {
        Map<TestResult, List<TestDetail>> results = new EnumMap<>(TestResult.class);
        for (TestResult result : TestResult.values()) {
            results.put(result, new ArrayList<>());
        }

        try {
            validateTestClass(clazz);
            Object testInstance = clazz.getDeclaredConstructor().newInstance();
            var methods = clazz.getDeclaredMethods();

            // Методы BeforeSuite и AfterSuite
            var beforeSuiteMethods = getAnnotatedMethods(methods, BeforeSuite.class, true);
            var afterSuiteMethods = getAnnotatedMethods(methods, AfterSuite.class, true);

            // Методы BeforeEach и AfterEach
            var beforeEachMethods = getAnnotatedMethods(methods, BeforeEach.class, false);
            var afterEachMethods = getAnnotatedMethods(methods, AfterEach.class, false);

            // Тестовые методы
            var testMethods = getTestMethods(methods);

            // Выполняем BeforeSuite
            executeMethods(beforeSuiteMethods, testInstance, results);

            // Выполняем тесты
            for (Method testMethod : testMethods) {
                if (testMethod.isAnnotationPresent(Disabled.class)) {
                    results.get(TestResult.SKIPPED).add(
                        new TestDetail(TestResult.SKIPPED, getTestName(testMethod), null));
                    continue;
                }

                // Выполняем BeforeEach
                executeMethods(beforeEachMethods, testInstance, results);

                // Выполняем тест
                var testDetail = executeTest(testMethod, testInstance);
                results.get(testDetail.result()).add(testDetail);

                // Выполняем AfterEach
                executeMethods(afterEachMethods, testInstance, results);
            }

            // Выполняем AfterSuite
            executeMethods(afterSuiteMethods, testInstance, results);

        } catch (BadTestClassError e) {
            throw e;
        } catch (Exception e) {
            throw new BadTestClassError("Failed to run tests: " + e.getMessage());
        }
        return results;
    }

    private static void validateTestClass(Class<?> c) {
        try {
            // Проверяем возможность создания экземпляра
            //            c.getDeclaredConstructor().newInstance();
            c.getConstructor().newInstance();
        } catch (Exception e) {
            throw new BadTestClassError("Не можем создать экземпляр тестового класса: " + e.getMessage());
        }

        for (Method method : c.getDeclaredMethods()) {
            // Проверяем, что BeforeEach, AfterEach и Test не статические
            if ((method.isAnnotationPresent(BeforeEach.class) ||
                method.isAnnotationPresent(AfterEach.class) ||
                method.isAnnotationPresent(Test.class)) &&
                Modifier.isStatic(method.getModifiers())) {
                throw new BadTestClassError(
                    "Методы аннотированные @BeforeEach, @AfterEach или @Test не могут быть статичным: " + method.getName());
            }

            // Проверяем, что BeforeSuite и AfterSuite статические
            if ((method.isAnnotationPresent(BeforeSuite.class) ||
                method.isAnnotationPresent(AfterSuite.class)) &&
                !Modifier.isStatic(method.getModifiers())) {
                throw new BadTestClassError(
                    "Методы аннотированные @BeforeSuite или @AfterSuite должны быть статичными: " + method.getName());
            }
        }
    }

    private static List<Method> getAnnotatedMethods(Method[] methods, Class<? extends Annotation> annotation, boolean requireStatic) {
        return Arrays.stream(methods)
            .filter(m -> m.isAnnotationPresent(annotation))
            .filter(m -> !requireStatic || Modifier.isStatic(m.getModifiers()))
            .collect(Collectors.toList());
    }

    private static List<Method> getTestMethods(Method[] methods) {
        return Arrays.stream(methods)
            .filter(m -> m.isAnnotationPresent(Test.class))
            .filter(m -> !m.isAnnotationPresent(BeforeSuite.class))
            .filter(m -> !m.isAnnotationPresent(AfterSuite.class))
            .filter(m -> !m.isAnnotationPresent(AfterEach.class))
            .filter(m -> !m.isAnnotationPresent(BeforeEach.class))
            .sorted(Comparator.comparingInt((Method m) -> {
                // Не стал заморачиваться с нормализацией, примем в нашей парадигме, что для всех порядок приоритетов ОДИНАКОВЫЙ
                var order = m.getAnnotation(Order.class);
                var test = m.getAnnotation(Test.class);
                return order != null ? order.priority() : test.priority();
            }).thenComparing(Method::getName).reversed())
            .toList();
    }

    private static TestDetail executeTest(Method testMethod, Object instance) {
        var testName = getTestName(testMethod);
        try {
            testMethod.setAccessible(true);
            testMethod.invoke(instance);
            return new TestDetail(TestResult.SUCCESS, testName, null);
        } catch (InvocationTargetException e) {
            var cause = e.getCause();
            if (cause instanceof TestAssertionError) {
                return new TestDetail(TestResult.FAILED, testName, cause);
            } else {
                return new TestDetail(TestResult.ERROR, testName, cause);
            }
        } catch (Exception e) {
            return new TestDetail(TestResult.ERROR, testName, e);
        }
    }

    private static void executeMethods(List<Method> methods, Object instance, Map<TestResult, List<TestDetail>> results) {
        for (Method method : methods) {
            try {
                method.setAccessible(true);
                method.invoke(Modifier.isStatic(method.getModifiers()) ? null : instance);
            } catch (Exception e) {
                results.get(TestResult.ERROR).add(
                    new TestDetail(TestResult.ERROR, method.getName(), e.getCause()));
            }
        }
    }

    private static String getTestName(Method method) {
        var testAnnotation = method.getAnnotation(Test.class);
        return testAnnotation.name().isEmpty() ? method.getName() : testAnnotation.name();
    }
}

package ru.otus.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public final class TestRunner {

    private static final Logger log = LoggerFactory.getLogger(TestRunner.class);

    private TestRunner() {
    }

    public static void runTests(Class<?> clazz) {

        Optional<Method> beforeMethodOptional = getAnnotatedMethod(clazz, Before.class);
        Optional<Method> afterMethodOptional = getAnnotatedMethod(clazz, After.class);
        List<Method> testMethods = getAnnotatedMethods(clazz, Test.class);

        int successful = 0;
        int failed = 0;

        for (Method testMethod : testMethods) {
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                invoke(instance, beforeMethodOptional);
                testMethod.invoke(instance);
                invoke(instance, afterMethodOptional);
                successful++;
            } catch (InvocationTargetException e) {
                Throwable cause = e.getCause();
                if (cause instanceof AssertionError || cause instanceof RuntimeException) {
                    log.error("Test {} - failed: {}", testMethod.getName(), cause.getMessage());
                    failed++;
                } else {
                    throw new RuntimeException(e);
                }
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e);
            }
        }

        log.info("\nTests:\n\ttotal: {}\n\tsuccesful: {}\n\tfailed: {}\n", testMethods.size(), successful, failed);
    }

    private static void invoke(Object object, Optional<Method> optionalMethod) throws InvocationTargetException, IllegalAccessException {
        if (optionalMethod.isPresent()) {
            optionalMethod.get().invoke(object);
        }
    }

    private static List<Method> getAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
        if (clazz == null
                || clazz.getDeclaredMethods().length == 0
                || annotation == null) {
            return Collections.emptyList();
        }
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .toList();
    }

    private static Optional<Method> getAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
        if (clazz == null
                || clazz.getDeclaredMethods().length == 0
                || annotation == null) {
            return Optional.empty();
        }
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .findFirst();
    }
}

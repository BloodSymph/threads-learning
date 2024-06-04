package org.example;

import java.util.stream.IntStream;

import static java.util.Arrays.fill;
import static java.util.Arrays.stream;

public class Main {
    public static void main(String[] args) {
        final EvenNumberGenerator generator = new EvenNumberGenerator();

        final int taskCounts = 10000;
        final Runnable generatingTask = () -> IntStream.range(0, taskCounts).forEach(i -> generator.generate());

        final int amountOfGeneratingThreads = 5;
        final Thread[] generatingThreads = createThreads(generatingTask, amountOfGeneratingThreads);

        startThreads(generatingThreads);
        waitUntilFinish(generatingThreads);

        final int expectedGeneratorValue = amountOfGeneratingThreads * taskCounts * 2;
        final int actualGeneratorValue = generator.getValue();
        if (expectedGeneratorValue != actualGeneratorValue) {
            throw new RuntimeException("Exception");
        }
    }

    private static Thread[] createThreads(final Runnable task, final int amountOfThreads){
        return IntStream.range(0, amountOfThreads).mapToObj(i -> new Thread(task)).toArray(Thread[]::new);
    }

    private static void startThreads(final Thread[] threads) {
        stream(threads).forEach(Thread::start);
    }

    private static void waitUntilFinish(final Thread[] threads) {
        stream(threads).forEach(Main::waitUntilFinish);
    }

    private static void waitUntilFinish(final Thread thread) {
        try {
            thread.join();
        } catch (final InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

}
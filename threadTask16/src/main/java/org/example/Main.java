package org.example;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        final Counter counter = new Counter();

        final int incrementAmount = 10;

        final int decrementAmount = 10;

        final Thread incrementingThread = new Thread(
                createTaskForCounter(
                        counter,
                        i -> counter.incrementValue(),
                        incrementAmount
                )
        );

        final Thread decrementingThread = new Thread(
                createTaskForCounter(
                        counter,
                        i -> counter.decrementValue(),
                        decrementAmount
                )
        );

        startThreads(incrementingThread, decrementingThread);
        waitUntilFinishing(incrementingThread, decrementingThread);

        System.out.printf("Counter`s value: %d.\n", counter.getValue());

    }

    private static void startThreads(final Thread... threads) {
        Arrays.stream(threads).forEach(Thread::start);
    }

    private static void waitUntilFinishing(final Thread... threads) {
        Arrays.stream(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    private static Runnable createTaskForCounter(final Counter counter,
                                                  final IntConsumer intConsumer,
                                                  final int times) {

        return () -> {
            counter.lock();
            try {
                IntStream.range(0, times).forEach(intConsumer);
            } finally {
                counter.unlock();
            }
        };

    }

    private static final class Counter {

        private final Lock lock = new ReentrantLock();

        private int value;

        public void lock () {
            this.lock.lock();
            printMessageWithCurrentThreadArguments("Thread '%s' locked counter.\n");
        }

        public void incrementValue() {
            this.value++;
            printMessageWithCurrentThreadArguments("Thread '%s' incremented counter.\n ");
        }

        public void decrementValue() {
            this.value--;
            printMessageWithCurrentThreadArguments("Thread '%s' decremented counter.\n ");
        }

        public void unlock() {
            printMessageWithCurrentThreadArguments("Thread '%s' unlocked counter.\n");
            this.lock.unlock();
        }

        public int getValue() {
            return value;
        }

        private static void printMessageWithCurrentThreadArguments(String message) {
            System.out.printf(message, Thread.currentThread().getName());
        }

    }
}
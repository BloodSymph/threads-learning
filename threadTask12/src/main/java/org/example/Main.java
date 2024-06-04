package org.example;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class Main {

    private static int firstCounter = 0;

    private static final int AMOUNT_FIRST = 500;

    private static final int AMOUNT_SECOND = 600;

    public static void main(String[] args) throws InterruptedException {

        final Thread firstThread = counterThread(AMOUNT_FIRST, i -> incrementCounter());
        firstThread.start();

        final Thread secondThread = counterThread(AMOUNT_SECOND, i -> incrementCounter());
        secondThread.start();

        firstThread.join();
        secondThread.join();

        System.out.println(firstCounter);
    }

    private static void incrementCounter() {
        synchronized (Runnable.class) {
            firstCounter++;
        }
    }

    private static Thread counterThread(
            final int amount,
            final IntConsumer intConsumer) {

        return new Thread(() -> IntStream.range(0, amount).forEach(intConsumer));
    }

}
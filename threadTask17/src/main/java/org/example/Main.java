package org.example;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        final BoundedBuffer<Integer> boundedBuffer = new BoundedBuffer<>(5);

        final Runnable producingTask = () -> Stream
                .iterate(0, i -> i + 1)
                .forEach(integer -> {
                    try {
                        boundedBuffer.put(integer);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });

        final Thread producingThread = new Thread(producingTask);

        final Runnable consumingTask = () -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    boundedBuffer.take();
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        final Thread consumingThread = new Thread(consumingTask);

        producingThread.start();
        consumingThread.start();

    }
}
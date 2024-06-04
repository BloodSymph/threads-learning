package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        final List<Integer> value  = Collections.synchronizedList(new ArrayList<>());
//        final Runnable task = () -> IntStream.range(0, 1000).forEach(value::add);
//
//        final Thread firstThread = new Thread(task);
//        final Thread secondThread = new Thread(task);
//
//        firstThread.start();
//        secondThread.start();
//
//        firstThread.join();
//        secondThread.join();
//
//        System.out.println(value.size());

        final List<Integer> value = new Vector<>();
        value.add(1);
        value.add(2);
        value.add(3);

        final Runnable addingTask = () -> addIfNotExist(value, 4);

        final Thread firstThread = new Thread(addingTask);
        final Thread secondThread = new Thread(addingTask);

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        System.out.println(value);
    }

    private static void addIfNotExist(
            final List<Integer> value, final Integer element) {

        try {
            if (!value.contains(element)) {
                TimeUnit.MILLISECONDS.sleep(100);
                value.add(element);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
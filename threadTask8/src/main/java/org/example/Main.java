package org.example;

public class Main {
    public static void main(String[] args) {
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);

        Thread thread = new Thread(() -> {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            printNameAndPriority(Thread.currentThread());
        });
        thread.start();

        printNameAndPriority(Thread.currentThread());
    }

    public static void printNameAndPriority(Thread thread) {
        System.out.println(thread.getName() + " " + thread.getPriority());
    }
}
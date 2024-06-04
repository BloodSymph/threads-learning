package org.example;

public class Task implements Runnable{

    private static final String ERROR_MESSAGE = "Something wrong! :)";

    @Override
    public void run() {
        System.out.println(Thread.currentThread().isDaemon());
        throw new RuntimeException(ERROR_MESSAGE);
    }
}

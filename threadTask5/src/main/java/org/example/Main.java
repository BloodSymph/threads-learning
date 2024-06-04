package org.example;

import java.util.concurrent.TimeUnit;

public class Main {

    private static final int DURATION = 1;

    public static void main(String[] args) throws InterruptedException {
        Thread communicated = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    doRequest();
                }
            } catch (InterruptedException interruptedException) {
                interruptedException.fillInStackTrace();
            }
        });
        communicated.start();

        Thread stopThread = new Thread(() -> {
            if (isServerShouldBeOffed()) {
                communicated.interrupt();
                stopServer();
            }
        });
        stopThread.sleep(5000L);
        stopThread.start();
    }

    public static void doRequest() throws InterruptedException {
        System.out.println("Request");
//        TimeUnit.SECONDS.sleep(DURATION);
        System.out.println("Message was received");
    }

    public static boolean isServerShouldBeOffed() {
        return true;
    }

    public static void stopServer() {
        System.out.println("Stop server");
    }

}
package org.example;

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

        final Thread thread = new Thread(() -> {
            try {
                printNameAndDaemonStatus(Thread.currentThread());
                final Thread thread1 = new Thread(() -> {printNameAndDaemonStatus(Thread.currentThread());});
                thread1.setDaemon(false);
                thread1.start();
                thread1.join();
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            }
        });
        thread.setDaemon(true);
        thread.start();
        thread.join();
    }

    private static void printNameAndDaemonStatus(final Thread thread) {
        System.out.println(thread.getName() + " " + thread.isDaemon());
    }

}
package org.example;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Main {

    public static void main(String... args) {
        Thread thread = new Thread(new Task());
        thread.setDaemon(true);
        thread.start();

        System.out.println(thread.isDaemon());

        System.out.println("Main thread stop working");
    }

    public static final class Task implements Runnable {
        @Override
        public void run() {
                try {
                    while (true)  {
                        System.out.println("We are working");
                        SECONDS.sleep(2);
                    }
                } catch (InterruptedException e) {
                    currentThread().interrupt();
                }
        }
    }
}
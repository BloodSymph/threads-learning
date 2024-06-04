package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(
                ()-> {
                    try {
                        mainThread.join(2000L);
                        System.out.println(Thread.currentThread().getState());
                    } catch (InterruptedException exception) {
                        exception.fillInStackTrace();
                    }
                }
        );
        thread.start();
        Thread.sleep(1000L);
        System.out.println(thread.getState());
    }
}
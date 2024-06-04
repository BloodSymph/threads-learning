package org.example;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Loop loop = new Loop();
        Thread firstThread = new Thread(loop);
        firstThread.start();

        Thread secondThread = new Thread(loop);
        secondThread.sleep(1000L);
        secondThread.start();
    }
}
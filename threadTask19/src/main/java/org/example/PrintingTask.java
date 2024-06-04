package org.example;

import java.util.concurrent.TimeUnit;

public final class PrintingTask implements Runnable {

    private volatile boolean shouldPrint = true;


    public void setShouldPrint(boolean shouldPrint) {
        this.shouldPrint = shouldPrint;
    }

    public boolean isShouldPrint() {
        return shouldPrint;
    }

    @Override
    public void run() {
        try {
            while (this.shouldPrint) {
                System.out.println("I am working!");
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

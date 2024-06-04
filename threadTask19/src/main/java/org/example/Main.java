package org.example;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final PrintingTask printingTask = new PrintingTask();
        final Thread printingThread = new Thread(printingTask);


        printingThread.start();

        TimeUnit.SECONDS.sleep(5);

        printingTask.setShouldPrint(false);
        System.out.println("Printing should be stopped");
    }

}
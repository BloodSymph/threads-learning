package org.example;

import java.util.concurrent.ThreadFactory;

public class Main {

    private static final String MESSAGE_EXCEPTION_TEMPLATE =
            "Exception was throws with message '%s' in thread '%s'.\n";


    public static void main(String[] args) throws InterruptedException {
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (thread, exceptionHandler)
                -> System.out.printf(
                        MESSAGE_EXCEPTION_TEMPLATE,
                exceptionHandler.getMessage(),
                thread.getName()
        );

        final ThreadFactory factory = new DaemonFactory(uncaughtExceptionHandler);
        final Thread thread = factory.newThread(new Task());
        thread.start();
        thread.join();

//        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
//
//        final Thread thread = new Thread(new Task());
////        thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
//        thread.start();
    }
}
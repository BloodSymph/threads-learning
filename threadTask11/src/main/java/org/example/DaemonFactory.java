package org.example;

import java.util.concurrent.ThreadFactory;

public class DaemonFactory implements ThreadFactory {

    private final Thread.UncaughtExceptionHandler exceptionHandler;

    public DaemonFactory(Thread.UncaughtExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }


    @Override
    public Thread newThread(Runnable r) {
        final Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(exceptionHandler);
        thread.setDaemon(true);
        return thread;
    }
}

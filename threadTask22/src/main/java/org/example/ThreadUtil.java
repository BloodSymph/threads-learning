package org.example;

import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public final class ThreadUtil {

    public static Thread[] createThreads(final Runnable task, final int amountOfThreads) {
        return IntStream.range(0, amountOfThreads)
                .mapToObj(i -> new Thread(task))
                .toArray(Thread[]::new);
    }

    public static void startThreads(final Thread[] threads) {
        stream(threads).forEach(Thread::start);
    }

    private ThreadUtil() {
        throw new UnsupportedOperationException();
    }

}

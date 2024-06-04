package org.example;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.stream.IntStream.range;

public class Main {

    public static void main(String[] args)
            throws InterruptedException {

//      testCounter(CounterGuardedByLock::new); // Amount of readings value: 683973838

        testCounter(CounterGuardedByReadWriteLock::new); //Amount of readings value: 28381382

    }

    private static void testCounter(final Supplier<? extends AbstractCounter> counterFactory)
            throws InterruptedException {

        final AbstractCounter counter = counterFactory.get();

        final int amountOfThreadsGettingValue = 50;

        final ReadingValueTask[] readingValueTasks = createReadingTasks(
                counter,
                amountOfThreadsGettingValue
        );

        final Thread[] readingValueThreads = mapToThreads(readingValueTasks);

        final Runnable incrementingCounterTask = createIncrementingCounterTask(counter);

        final int amountOfThreadsIncrementingCounter = 2;

        final Thread[] incrementingCounterThreads = createThreads(
                incrementingCounterTask,
                amountOfThreadsIncrementingCounter
        );

        startThreads(readingValueThreads);
        startThreads(incrementingCounterThreads);

        TimeUnit.SECONDS.sleep(5);

        interruptThreads(readingValueThreads);
        interruptThreads(incrementingCounterThreads);

        waitUntilFinish(readingValueThreads);

        final long totalAmountOfReadings = findTotalAmountOfTreads(
                readingValueTasks);

        System.out.printf(
                "Amount of readings value: %d",
                totalAmountOfReadings
        );

    }

    private static ReadingValueTask[] createReadingTasks(final AbstractCounter counter,
                                                         final int amountOfTasks) {

        return range(0, amountOfTasks)
                .mapToObj(i -> new ReadingValueTask(counter))
                .toArray(ReadingValueTask[]::new);

    }

    private static Thread[] mapToThreads(final Runnable[] tasks) {
        return Arrays.stream(tasks)
                .map(Thread::new)
                .toArray(Thread[]::new);
    }

    private static Runnable createIncrementingCounterTask(final AbstractCounter counter) {
        return () -> {
          while (!Thread.currentThread().isInterrupted()) {
              incrementCounter(counter);
          }
        };
    }

    private static void incrementCounter(final AbstractCounter counter) {
        try {
            counter.increment();
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    private static Thread[] createThreads(final Runnable task,
                                          final int amountOfThreads) {
        return range(0, amountOfThreads)
                .mapToObj(i -> new Thread(task))
                .toArray(Thread[]::new);
    }

    private static void startThreads(final Thread[] threads) {
        forEach(threads, Thread::start);
    }

    private static void interruptThreads(final Thread[] threads) {
        forEach(threads, Thread::interrupt);
    }

    private static void forEach(final Thread[] threads, final Consumer<Thread> action) {
        Arrays.stream(threads).forEach(action);
    }

    private static void waitUntilFinish(final Thread[] threads) {
        forEach(threads, Main::waitUntilFinish);
    }

    private static void waitUntilFinish(final Thread thread) {
        try {
            thread.join();
        } catch (InterruptedException interruptedException) {
            Thread.currentThread().interrupt();
        }
    }

    private static long findTotalAmountOfTreads(final ReadingValueTask[] tasks) {
        return Arrays.stream(tasks)
                .mapToLong(ReadingValueTask::getAmountOfReads)
                .sum();
    }

    private static final class ReadingValueTask implements Runnable {

        private final AbstractCounter counter;

        private long amountOfReads;

        public ReadingValueTask(final AbstractCounter counter) {
            this.counter = counter;
        }

        public long getAmountOfReads() {
            return this.amountOfReads;
        }


        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                this.counter.getValue();
                this.amountOfReads++;
            }
        }
    }

}